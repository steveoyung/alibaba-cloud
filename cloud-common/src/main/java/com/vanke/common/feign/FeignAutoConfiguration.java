package com.vanke.common.feign;

import com.vanke.common.tracer.TracerHelper;
import com.vanke.common.tracer.TracerKey;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * set x-tracer for all http request
 */
@Configuration
public class FeignAutoConfiguration implements RequestInterceptor {
    static final Logger logger = LoggerFactory.getLogger(FeignAutoConfiguration.class);
    @Override
    public void apply(RequestTemplate template) {
        String tracerid = TracerHelper.create().get();
        logger.info("x-tracer feign output: {}", tracerid);
        template.header(TracerKey.TRACER_HEADER_KEY, tracerid);
        tracerid = null;
    }
}
