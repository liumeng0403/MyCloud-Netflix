package org.mydal.servicec.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-b")
public interface HelloRemote {

    @RequestMapping(value = "/bhello/{name}")
    public String hello(@PathVariable("name") String name);

}
