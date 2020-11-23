package org.mydal.servicec.remote;

import org.mydal.common.po.ServiceADemo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "service-b")
public interface HelloRemote {

    @RequestMapping("/bData")
    public List<ServiceADemo> queryData();

}
