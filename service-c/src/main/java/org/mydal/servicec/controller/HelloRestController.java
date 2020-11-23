package org.mydal.servicec.controller;

import org.mydal.common.po.ServiceADemo;
import org.mydal.servicec.remote.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class HelloRestController {

    @SuppressWarnings("all")
    @Autowired
    private HelloRemote helloRemote;

    @RequestMapping(value = "/chello", method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/data/all",method = RequestMethod.GET)
    @ResponseBody
    public List<ServiceADemo> testFull(){
        return helloRemote.queryData();
    }

}
