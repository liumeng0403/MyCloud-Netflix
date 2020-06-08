package org.mydal.servicea.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/ahello")
    public String index(@RequestParam String name) {
        return " service-a --- "+name;
    }
}
