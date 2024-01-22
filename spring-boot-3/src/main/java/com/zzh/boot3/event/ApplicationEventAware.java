package com.zzh.boot3.event;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/21 17:54
 */
public class ApplicationEventAware extends ApplicationEvent {


    public ApplicationEventAware(Object source,ApplicationListenerPublisher applicationListenerPublisher) {
        super(source);
        applicationListenerPublisher.publishEvent(this);
    }

    public ApplicationEventAware(Object source, Clock clock) {
        super(source, clock);
    }
}
