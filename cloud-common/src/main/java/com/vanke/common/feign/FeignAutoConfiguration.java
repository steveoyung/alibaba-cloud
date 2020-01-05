package com.vanke.common.feign;

import com.vanke.common.tracer.RequestTracer;
import com.vanke.common.tracer.TracerHelper;
import com.vanke.common.tracer.TracerKey;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * set x-tracer for all http request
 */
@Configuration
@ConditionalOnClass(RequestInterceptor.class)
public class FeignAutoConfiguration implements RequestInterceptor {
    static final Logger logger = LoggerFactory.getLogger(FeignAutoConfiguration.class);
    @Override
    public void apply(RequestTemplate template) {
        logger.info("thread id: {}", Thread.currentThread().getId());
        String tracer = TracerHelper.create().getID();
        logger.info("x-tracer feign output: {}", tracer);
        template.header(TracerKey.TRACER_HEADER_KEY, tracer);
        tracer = null;
    }
}
