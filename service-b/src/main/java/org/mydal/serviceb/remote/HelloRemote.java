package org.mydal.serviceb.remote;

import org.mydal.common.po.ServiceADemo;
import org.mydal.serviceb.hystrix.HelloRemoteHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-a",fallback = HelloRemoteHystrix.class)
public interface HelloRemote {

    @RequestMapping(value = "/ahello")
    public String hello(@RequestParam(value = "name") String name);

    @RequestMapping("/aDbData")
    public List<ServiceADemo> queryDb();
}
