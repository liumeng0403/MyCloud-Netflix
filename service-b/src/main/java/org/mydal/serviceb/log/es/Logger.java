package org.mydal.serviceb.log.es;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.joda.time.DateTime;
import org.mydal.serviceb.log.LogMessage;
import org.mydal.serviceb.log.common.SnowFlake;
import org.mydal.serviceb.log.es.index.IndexAuto;
import org.mydal.serviceb.log.es.index.TypeAuto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Logger {

    @Autowired
    private TransportClient client;

    @Autowired
    private IndexAuto index;

    @Autowired
    private TypeAuto type;

    public void log(LogMessage log){
        index.autoIndex();
        type.autoType();
        log.setId(SnowFlake.nextId());
        Map<String,Object> map = new HashMap<>();
        map.put("ID",log.getId());
        map.put("Ikey",log.getIkey());
        map.put("Username",log.getUsername());
        map.put("LogTime",new DateTime(log.getLogTime()).toString("yyyy-MM-dd HH:mm:ss"));
        map.put("ClientIP",log.getClientIP());
        map.put("ServerIP",log.getServerIP());
        map.put("Module",log.getModule());
        map.put("OrderNo",log.getOrderNo());
        map.put("LogType",log.getLogType());
        map.put("Keyword",log.getKeyword());
        map.put("Content",log.getContent());
        map.put("IsHandle",log.getIsHandle());
//        map.put("json", JsonUtil.getJackson().toJson(log));
        IndexResponse response =  client
                .prepareIndex(index.getIndexName(),"log",log.getId().toString())
                .setSource(map).get();
        Object obj = response.status();
    }

}
