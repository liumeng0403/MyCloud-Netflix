package org.mydal.serviceb.hystrix;

import org.mydal.serviceb.remote.HelloRemote;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class HelloRemoteHystrix implements HelloRemote {
    @Override
    public String hello(@RequestParam(value = "name") String name){
        return "hello " +name+", this messge send failed ";
    }
}
