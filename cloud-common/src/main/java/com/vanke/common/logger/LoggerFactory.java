package com.vanke.common.logger;

import org.slf4j.Logger;

public class LoggerFactory {

    public static final Logger getLogger(Class<?> clazz){
        Logger logger = org.slf4j.LoggerFactory.getLogger(clazz);
        return new XLoggerDecoration(logger);
    }
}
