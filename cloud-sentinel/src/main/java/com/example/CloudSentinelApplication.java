package com.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.alibaba.sentinel.annotation.SentinelRestTemplate;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@ServletComponentScan(value = {"com.example", "com.vanke"})
@ComponentScan(value = {"com.example", "com.vanke"})
public class CloudSentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudSentinelApplication.class, args);
    }

    @Bean
    @SentinelRestTemplate(blockHandler = "handleException", blockHandlerClass = ExceptionUtil.class)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

