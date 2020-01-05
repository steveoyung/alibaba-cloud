package com.example;

import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NacosConfigDataListener {
    static final Logger logger = LoggerFactory.getLogger(NacosConfigDataListener.class);
    @NacosConfigListener(dataId = "sentinel-data", groupId = "alibaba-cloud")
    public void listener(Object datas){
        logger.info("nacos data: {}" , datas);
    }
}
