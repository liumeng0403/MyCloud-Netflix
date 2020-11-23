package org.mydal.servicea.controller;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.mydal.common.po.ServiceADemo;
import org.mydal.common.po.YmlDTO;
import org.mydal.servicea.mapper.ServiceADemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private ServiceADemoMapper dbMapper;

    @Autowired
    private YmlDTO ymlDTO;

    @RequestMapping("/ahello")
    public String index(@RequestParam String name) {
        return " service-a --- hello,  "+name;
    }

    @RequestMapping("/aDbData")
    public List<ServiceADemo> queryDb() {
        List<ServiceADemo> list = dbMapper.queryAll();
        list.forEach(item->{
            item.setText(item.getText() + "|||" + ymlDTO.getLocalVaue());
        });
        return list;
    }

}
