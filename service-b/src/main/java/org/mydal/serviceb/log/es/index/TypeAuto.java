package org.mydal.serviceb.log.es.index;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class TypeAuto {

    @Autowired
    private TransportClient client;

    @Autowired
    private IndexAuto index;

    public void autoType() {
        TypesExistsResponse typesExists = client.admin().indices()
                .prepareTypesExists(index.getIndexName())
                .setTypes("log")
                .get();
        if (typesExists.isExists()) {
            return;
        } else {
            PutMappingResponse response = null;
            try {
                XContentBuilder mapping = XContentFactory.jsonBuilder()
                        .startObject()
                        .startObject("properties")
                        .startObject("ID")
                        .field("type", "keyword")
                        .endObject()
                        .startObject("Ikey")   //  精准
                        .field("type", "keyword")
                        .endObject()
                        .startObject("Username")   //  模糊
                        .field("type", "text")
                        .field("analyzer","ik_smart")
                        .field("search_analyzer","ik_smart")
                        .endObject()
                        .startObject("LogTime")   //   时间段
                        .field("type", "date")
                        .field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
                        .endObject()
                        .startObject("ClientIP")   //
                        .field("type", "ip")
                        .endObject()
                        .startObject("ServerIP")   //
                        .field("type", "ip")
                        .endObject()
                        .startObject("Module")    //  精准
                        .field("type", "keyword")
                        .endObject()
                        .startObject("OrderNo")   //  精准
                        .field("type", "keyword")
                        .endObject()
                        .startObject("LogType")   //  精准
                        .field("type", "keyword")
                        .endObject()
                        .startObject("Keyword")   //  模糊
                        .field("type", "text")
                        .field("analyzer","ik_smart")
                        .field("search_analyzer","ik_smart")
                        .endObject()
                        .startObject("Content")    //  模糊
                        .field("type", "text")
                        .field("analyzer","ik_smart")
                        .field("search_analyzer","ik_smart")
                        .endObject()
                        .startObject("IsHandle")   //  精准
                        .field("type", "boolean")
                        .endObject()
                        .endObject()
                        .endObject();
                //添加mapping 绑定到 index
                PutMappingRequest putMappingRequest = Requests.putMappingRequest(index.getIndexName()).type("log").source(mapping);
                response = client.admin().indices().putMapping(putMappingRequest).actionGet();
            } catch (IOException e) {
                log.info("创建 type log 失败!");
                return;
            }
            if(response.isAcknowledged()){
                log.info("创建 type log 成功!");
                return;
            }else {
                log.info("创建 type log 失败!");
                return;
            }
        }
    }
}
