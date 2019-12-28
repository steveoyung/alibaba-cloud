package com.vanke.common.tracer;

import java.util.UUID;

public class TracerIDGenerator {
    public static final String create(){
        return UUID.randomUUID().toString();
    }
}
