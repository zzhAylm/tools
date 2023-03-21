package com.zzh.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/3/20 21:02
 */
@Component
public class VoiceEventPublisher {

    @Autowired
    private ApplicationEventPublisher publisher;

    // 事件的的发布
    public void publish(String message) {
        publisher.publishEvent(new VoiceEvent(this, message));
    }

}
