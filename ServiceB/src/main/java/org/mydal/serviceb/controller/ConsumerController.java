package org.mydal.serviceb.controller;

import org.mydal.serviceb.remote.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @SuppressWarnings("all")
    @Autowired
    private HelloRemote HelloRemote;

    @RequestMapping("/bhello/{name}")
    public String index(@PathVariable("name") String name) {
        String astr= HelloRemote.hello(name);
        String astr2 = HelloRemote.hello(name);
        return "service-b --- " + astr + astr2;
    }

}
