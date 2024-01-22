package com.zzh.boot3.event;

import lombok.NonNull;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/21 17:52
 */
@Component
public class ApplicationListenerPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(ApplicationEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

    @Override
    public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
