package com.vanke.common.tracer;

import lombok.Data;

@Data
public class RequestTracer {
    public RequestTracer() {
    }

    public RequestTracer(String id, String host, Long start) {
        this.id = id;
        this.host = host;
        this.start = start;
    }

    private String id;

    private String host;

    private Long start;
}
