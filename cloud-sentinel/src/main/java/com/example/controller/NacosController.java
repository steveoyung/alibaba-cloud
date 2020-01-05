//package com.example.controller;
//
//import com.alibaba.nacos.api.config.annotation.NacosValue;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class NacosController {
//
//    @Value("${gateway.thirdparty.datas.data1.value}")
//    private String testValue;
//
//    @GetMapping(name = "/nacos/value")
//    public String testNacos(){
//        return "hello " + this.testValue;
//    }
//}
