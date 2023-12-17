package com.zzh.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class MyEventPublisher implements ApplicationEventPublisherAware, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private ApplicationEventPublisher applicationEventPublisher;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent() {
        logger.info("开始发布自定义事件MyEvent");
        MyEvent myEvent = new MyEvent(applicationContext);
        applicationEventPublisher.publishEvent(myEvent);
        logger.info("发布自定义事件MyEvent结束");
    }
}
