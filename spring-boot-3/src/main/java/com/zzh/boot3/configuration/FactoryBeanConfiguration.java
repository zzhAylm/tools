package com.zzh.boot3.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/5/10 11:06
 */
@Configuration
public class FactoryBeanConfiguration {


    @Bean
    public TestFactoryBean testFactoryBean() {
        return new TestFactoryBean();
    }
}
