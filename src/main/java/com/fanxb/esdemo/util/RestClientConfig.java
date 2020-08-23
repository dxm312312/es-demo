package com.fanxb.esdemo.util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class RestClientConfig extends AbstractElasticsearchConfiguration {
    @Override
    public RestHighLevelClient elasticsearchClient() {
        RestHighLevelClient client =
                new RestHighLevelClient(RestClient.builder(
                        new HttpHost("127.0.0.1", 9201, "http"),
                        new HttpHost("127.0.0.1", 9202, "http"),
                        new HttpHost("127.0.0.1", 9203, "http")));
       // return RestClients.create(ClientConfiguration.localhost()).rest();
        return client;
    }


}
