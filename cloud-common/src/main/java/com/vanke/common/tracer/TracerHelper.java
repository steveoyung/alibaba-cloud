package com.vanke.common.tracer;

import org.springframework.core.NamedThreadLocal;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class TracerHelper {
    private static ThreadLocal<RequestTracer> TRACER_LOCAL = new NamedThreadLocal("Tracer");
    static final TracerIDGenerator tracerGenerator = new TracerIDGenerator();
    private TracerHelper(){};

    private static final TracerHelper instance = new TracerHelper();

    public static final TracerHelper create(){
        return instance;
    }

    private RequestTracer buildTracer(HttpServletRequest request){
        String tracerID = request.getHeader(TracerKey.TRACER_HEADER_KEY);
        if(StringUtils.isEmpty(tracerID)){
            tracerID = tracerGenerator.create();
        }
        Long start = System.currentTimeMillis();
        String host =request.getRemoteHost();
        request.setAttribute(TracerKey.TRACER_HEADER_KEY, tracerID);
        return new RequestTracer(tracerID, host, start);
    }

    public void set(HttpServletRequest request){
        TRACER_LOCAL.set(this.buildTracer(request));
    }

    public void set(String traceID){
        RequestTracer localtracer = new RequestTracer();
        localtracer.setId(traceID);
        TRACER_LOCAL.set(localtracer);
    }

    public RequestTracer get(){
        return TRACER_LOCAL.get();
    }

    public String getID(){
        return get() == null ? null : get().getId();
    }

    public void clear(){
        TRACER_LOCAL.remove();
    }


}
