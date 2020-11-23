package org.mydal.serviceb.log.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

@Slf4j
@Configuration
public class TransportConfig {

    @Value("${spring.data.elasticsearch.cluster-name}")
    private String clusterName;

    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusterNodes;

    @Bean
    public TransportClient getTransportClient() {
        log.info("elasticsearch init.");
        if (StringUtils.isEmpty(clusterName)) {
            throw new RuntimeException("elasticsearch.cluster-name is empty.");
        }
        if (StringUtils.isEmpty(clusterNodes)) {
            throw new RuntimeException("elasticsearch.cluster-nodes is empty.");
        }
        try {
            Settings settings = Settings.builder()
                    .put("cluster.name", clusterName.trim())
                    .put("client.transport.sniff", true)
                    .build();
            TransportClient transportClient = new PreBuiltTransportClient(settings);
            String[] clusterNodeArray = clusterNodes.trim().split(",");
            for (String clusterNode : clusterNodeArray) {
                String[] clusterNodeInfoArray = clusterNode.trim().split(":");
                TransportAddress transportAddress = new TransportAddress(
                        InetAddress.getByName(clusterNodeInfoArray[0]),
                        Integer.parseInt(clusterNodeInfoArray[1]));
                transportClient.addTransportAddress(transportAddress);
            }
            log.info("elasticsearch init success.");
            return transportClient;
        } catch (Exception e) {
            throw new RuntimeException("elasticsearch init fail.");
        }
    }
}
