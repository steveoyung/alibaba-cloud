package com.vanke.common.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class UserExceptionCommand extends HystrixCommand<String> {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserTimeOutCommand.class);

    public UserExceptionCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("userGroup"));
    }

    @Override
    protected String run() throws Exception {
        LOGGER.info("start query exception endpoint");
        URL url = new URL("http://localhost:8002/user/exception");
        byte[] result = new byte[1024];
        url.openStream().read(result);
        return new String(result);
    }

    @Override
    protected String getFallback() {
        return "服务降级，暂时不可用";
    }
}
