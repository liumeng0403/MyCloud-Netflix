package org.mydal.serviceb.hystrix;

import org.mydal.common.po.ServiceADemo;
import org.mydal.serviceb.remote.HelloRemote;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Component
public class HelloRemoteHystrix implements HelloRemote {
    @Override
    public String hello(@RequestParam(value = "name") String name){
        return " sevice-a 服务 hystrix, param:  " +name ;
    }

    @Override
    public List<ServiceADemo> queryDb(){
        List<ServiceADemo> result = new ArrayList<>();
        ServiceADemo data = new ServiceADemo();
        data.setId(0L);
        data.setText("sevice-a 服务 hystrix , 这里是处理. ");
        result.add(data);
        return result;
    }
}
