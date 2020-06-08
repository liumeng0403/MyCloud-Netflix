package org.mydal.serviceb.remote;

import org.mydal.serviceb.hystrix.HelloRemoteHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-a",fallback = HelloRemoteHystrix.class)
public interface HelloRemote {

    @RequestMapping(value = "/ahello")
    public String hello(@RequestParam(value = "name") String name);

}
