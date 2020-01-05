package com.vanke.common.tracer;

import javax.servlet.http.HttpServletRequest;

public class RequestContextHolder {
    private static RequestContextHolder REQUEST_CONTEXT_LOCAL = new RequestContextHolder();
    private RequestContextHolder(){}
    public static final RequestContextHolder getRequestContextLocal(){
        return REQUEST_CONTEXT_LOCAL;
    }

    public void setTracer(HttpServletRequest request){
        TracerHelper.create().set(request);
    }
    public RequestTracer getTracer(){
        return TracerHelper.create().get();
    }

    public void cleartracer(){
        TracerHelper.create().clear();
    }


}
