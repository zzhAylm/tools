package com.zzh.boot3.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/21 17:10
 */
@Slf4j
public class SpringBootApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
       log.info("application context is={}",applicationContext);
    }
}
