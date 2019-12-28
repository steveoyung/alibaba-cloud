package com.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-provider")
public interface IEchoService {

    @GetMapping("/echo/{appname}")
    public String echo(@PathVariable("appname") String appname);
}
