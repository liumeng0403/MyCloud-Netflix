package org.mydal.servicec.controller;

import org.mydal.servicec.remote.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    @SuppressWarnings("all")
    @Autowired
    private HelloRemote helloRemote;

    @RequestMapping(value = "/full/all",method = RequestMethod.GET)
    public String testFull(){
        String bstr = helloRemote.hello("Test Full !");
        return "service-c --- " + bstr;
    }

}
