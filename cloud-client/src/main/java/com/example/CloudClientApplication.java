package com.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 使用 @EnableDiscoveryClient 注解开启服务注册与发现功能
 * @author qinxuewu
 * @version 1.00
 * @time  18/1/2019 上午 10:36
 * @email 870439570@qq.com
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(value = {"com.example", "com.vanke"})
@ServletComponentScan(value = {"com.example", "com.vanke"})
public class CloudClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudClientApplication.class, args);
    }

}

