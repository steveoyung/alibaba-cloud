package com.vanke.common.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class TestCommand extends HystrixCommand<String> {
    protected TestCommand(HystrixCommandGroupKey group) {
        super(group);
    }

    @Override
    protected String run() throws Exception {
        return null;
    }
}
