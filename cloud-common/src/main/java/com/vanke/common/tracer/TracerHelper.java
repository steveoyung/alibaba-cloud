package com.vanke.common.tracer;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

public class TracerHelper {
    private ThreadLocal<String> tracer = new ThreadLocal<>();
    static final TracerIDGenerator tracerGenerator = new TracerIDGenerator();
    private TracerHelper(){};

    private static final TracerHelper instance = new TracerHelper();

    public static final TracerHelper create(){
        return instance;
    }

    public void set(HttpServletRequest request){
        String tracerID = request.getHeader(TracerKey.TRACER_HEADER_KEY);
        if(StringUtils.isEmpty(tracerID)){
            tracerID = tracerGenerator.create();
        }
        tracer.set(tracerID);
    }

    public String get(){
        return tracer.get();
    }

    public void clear(){
        tracer.remove();
    }


}
