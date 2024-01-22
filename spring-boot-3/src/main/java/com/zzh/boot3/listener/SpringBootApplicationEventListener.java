package com.zzh.boot3.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/21 16:36
 */
@Slf4j
@Component
public class SpringBootApplicationEventListener implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        log.info("spring application event listener is ready..........");
    }
}
