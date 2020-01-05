package com.example;

import com.vanke.common.feign.FeignAutoConfiguration;
import com.vanke.common.tracer.TracerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
public class ConsumerController {
    static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String appName;
    @Autowired
    private IEchoService echoService;
    /**
     * 通过带有负载均衡的RestTemplate 和 FeignClient 也是可以访问的
     * @return
     */
    @GetMapping("/echo/app-name")
    public String echoAppName(){
        //使用 LoadBalanceClient 和 RestTemolate 结合的方式来访问
        ServiceInstance serviceInstance = loadBalancerClient.choose("service-provider");
        String url = String.format("http://%s:%s/echo/%s",serviceInstance.getHost(),serviceInstance.getPort(),appName);
        System.out.println("request url:"+url);
        return restTemplate.getForObject(url,String.class);
    }


    /**
     * 通过带有负载均衡的RestTemplate 和 FeignClient 也是可以访问的
     * @return
     */
    @GetMapping("/echo/remote/{appname}")
    public String echoRemoteAppname(@PathVariable("appname") String appname){
        logger.info("thread id: {}", Thread.currentThread().getId());
        logger.info("controller tracer : {}", TracerHelper.create().get());
       return this.echoService.echo(appname);
    }


    /**
     * 通过带有负载均衡的RestTemplate 和 FeignClient 也是可以访问的
     * @return
     */
    @GetMapping("/echo/{appname}")
    public String echoPerfTest(@PathVariable("appname") String appname){
        return appname;
    }

}
