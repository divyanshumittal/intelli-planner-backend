package com.poc.application;

import com.poc.configuration.ElasticsearchConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.poc")
@EnableAutoConfiguration(exclude = {ElasticsearchConfiguration.class})
public class Application {
    public static void main(String args[]) {
        SpringApplication.run(Application.class);
    }
}
