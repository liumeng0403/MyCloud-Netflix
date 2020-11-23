package org.mydal.serviceb.log.es.index;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class IndexAuto {

    @Value("${jwn.elk.prefix}")
    private String prefix;

    @Value("${jwn.elk.save-days}")
    private Integer saveDays;

    @Autowired
    private TransportClient client;

    public String getIndexName(){
        return prefix + DateTime.now().toString("yyyy.MM.dd");
    }
    public String getOldIndexName(){
        return prefix + DateTime.now().minusDays(saveDays).toString("yyyy.MM.dd");
    }

    public Boolean indexExist(String indexName){
        IndicesAdminClient indicesAdminClient = client.admin().indices();
        IndicesExistsResponse response = indicesAdminClient.prepareExists(indexName).get();
        if(response.isExists()){
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }
    }

    public void autoIndex(){
        String indexName = getIndexName();
        if(indexExist(indexName)){
            return;
        }else {
            IndicesAdminClient indicesAdminClient = client.admin().indices();
            CreateIndexResponse ciResponse = indicesAdminClient
                    .prepareCreate(indexName)
                    .setSettings(Settings.builder()
                            .put("index.number_of_shards",3)
                            .put("index.number_of_replicas",0).build()).get();
            if(ciResponse.isAcknowledged()){

                log.info("创建 index : " + indexName + " 成功!");
                return;
            }else {
                log.info("创建 index : " + indexName + " 失败!");
                return;
            }
        }
    }

    public void deleteIndex(){
        String indexName = getOldIndexName();
        if(indexExist(indexName)){
            IndicesAdminClient indicesAdminClient = client.admin().indices();
            DeleteIndexResponse diResponse = indicesAdminClient.prepareDelete(indexName).get();
            if(diResponse.isAcknowledged()){
                log.info("删除 index : " + indexName + " 成功!");
                return;
            }else {
                log.info("删除 index : " + indexName + " 失败!");
                return;
            }
        }else {
            return;
        }
    }

    public void prepareIndex(){
        autoIndex();
        deleteIndex();
    }

}
