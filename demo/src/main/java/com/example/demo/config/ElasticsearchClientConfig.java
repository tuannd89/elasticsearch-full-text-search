package com.example.demo.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchClientConfig {

    @Value("${elasticsearch.address}")
    private String address;

    @Bean
    public RestHighLevelClient getRestHighLevelClient() {
        String[] addresses = address.split(",");
        HttpHost[] hosts = new HttpHost[addresses.length];
        for (int i = 0; i < addresses.length; i++) {
            hosts[i] = HttpHost.create(addresses[i]);
        }
        return new RestHighLevelClient(RestClient.builder(hosts));
    }
}
