package com.zzh.spring.beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.util.logging.Logger;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/6/27 19:45
 */
public class Bean1 {

    private static final Logger log = Logger.getGlobal();

    @PostConstruct
    public void init() {
        log.info("init method");
    }

    @PreDestroy
    public void destroy() {
        log.info("destroy");
    }
}
