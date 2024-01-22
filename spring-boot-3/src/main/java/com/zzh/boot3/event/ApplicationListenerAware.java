package com.zzh.boot3.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/21 17:57
 */
@Slf4j
@Component
public class ApplicationListenerAware implements ApplicationListener<ApplicationEventAware> {
    @Override
    public void onApplicationEvent(ApplicationEventAware event) {
        log.info("监听事件—>{}", event);
    }
}
