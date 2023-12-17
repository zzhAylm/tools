package com.zzh.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyAnnotationEventListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @EventListener
    public void onMyEventPublished(MyEvent myEvent) {
        logger.info("收到自定义事件MyEvent -- MyAnnotationEventListener");
    }
}
