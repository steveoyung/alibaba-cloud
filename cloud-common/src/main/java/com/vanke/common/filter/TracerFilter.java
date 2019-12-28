package com.vanke.common.filter;

import com.vanke.common.tracer.TracerHelper;
import jdk.internal.instrumentation.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName = "tracerFilter",urlPatterns = "/*")
public class TracerFilter implements Filter {
    static final Logger logger = LoggerFactory.getLogger(TracerFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try{
            TracerHelper.create().set((HttpServletRequest)request);
            logger.info("x-tracer inbound : {}", TracerHelper.create().get());
            chain.doFilter(request, response);
        }catch(Exception ex){
            logger.error("error occur: tracer: {}", TracerHelper.create().get());
            logger.error("error occur: {}", ex.fillInStackTrace());
        }finally {
            logger.info("x-tracer outbound : {}", TracerHelper.create().get());
            TracerHelper.create().clear();
        }
    }

    @Override
    public void destroy() {
    }
}
