package com.zzh.boot3.configuration;

import io.micrometer.core.aop.CountedAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/26 22:10
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MetricsConfiguration {

    @Bean
    public CountedAspect countedAspect() {
       return new CountedAspect();
    }

}
