package com.zzh.spring.guavaevent;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/3/20 21:37
 */
@Configuration
public class GuavaConfiguration {

    @Autowired
    private GuavaListener guavaListener;

    @Bean
    public EventBus eventBus() {
        EventBus eventBus = new EventBus();
        eventBus.register(guavaListener);
        return eventBus;
    }
}
