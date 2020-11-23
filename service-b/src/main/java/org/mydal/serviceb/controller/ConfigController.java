package org.mydal.serviceb.controller;

import org.joda.time.DateTime;
import org.mydal.serviceb.log.LogDemo;
import org.mydal.serviceb.log.LogMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RefreshScope // 使用该注解的类，会在接到SpringCloud配置中心配置刷新的时候，自动将新的配置更新到该类对应的字段中。
@RestController
public class ConfigController {

    @Value("${neo.hello}")
    private String configValue;

    @Autowired
    private LogDemo logDemo;

    @RequestMapping("/configTest")
    public String index() {
        return "现在配置neo.hello的值是:  " + configValue;
    }

    @RequestMapping("/elkLog")
    public String elkLog() {
        LogMessage msg = new LogMessage();
        msg.setIkey(UUID.randomUUID().toString());
        msg.setUsername("test name:" + Math.random() * 1000);
        msg.setLogTime(DateTime.now().toDate());
        msg.setModule("module : " + Math.random() * 10);
        msg.setOrderNo("No:" + Math.random()*1000000);
        msg.setLogType("biz");
        msg.setKeyword("keyword:" + Math.random()*10);
        msg.setContent("json:{xxx,yyy,zzz}");
        msg.setIsHandle(Boolean.TRUE);
        logDemo.logToEs(msg);
        return  "success:" + msg.getKeyword();
    }

}
