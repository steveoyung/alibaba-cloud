package com.vanke.common.filter;

import com.vanke.common.tracer.RequestContextHolder;
import com.vanke.common.tracer.TracerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName = "tracerFilter",urlPatterns = "/*")
public class TracerFilter implements Filter {
    static final Logger logger = LoggerFactory.getLogger(TracerFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        String traceID = null;
        try{
            logger.info("thread id: {}", Thread.currentThread().getId());
            TracerHelper.create().set((HttpServletRequest)request);
            traceID = RequestContextHolder.getRequestContextLocal().getTracer().getId();
            logger.info("x-tracer inbound : {}", traceID);
            chain.doFilter(request, response);
        }catch(Exception ex){
            logger.error("error occur: tracer: {}", traceID);
            logger.error("error occur: {}", ex.fillInStackTrace());
        }finally {
            logger.info("x-tracer outbound : {}", traceID);
            TracerHelper.create().clear();
        }
    }

    @Override
    public void destroy() {
    }
}
