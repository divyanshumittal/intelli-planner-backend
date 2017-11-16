package com.poc.configuration;

import javax.annotation.Resource;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@PropertySource(value = "classpath:elasticsearch.properties")
@EnableElasticsearchRepositories(basePackages = "com.poc")
public class ElasticsearchConfiguration {
    @Resource
    private Environment environment;

    @Bean
    public Client client() {
        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "uptake").put("client.transport.ping_timeout", "60s").put("client.transport.nodes_sampler_interval",
                "60s").build();
        TransportClient client = new TransportClient(settings);
        TransportAddress address = new InetSocketTransportAddress(environment.getProperty("elasticsearch.host"), Integer.parseInt(environment.getProperty("elasticsearch.port")));
        client.addTransportAddress(address);
        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(client());
    }
}
