package com.zzh.config;


import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class RejectPushConfiguration {

    @Bean(name = "rejectPushExecutor")
    public Executor rejectPushExecutor() {
        ThreadPoolTaskExecutor executor =new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(30);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(50000);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("reject-push-thread");
        executor.initialize();
        return executor;
    }

    @Bean
    public Executor monitorSettleExecutor() {
       return ExecutorServiceMetrics.monitor(Metrics.globalRegistry, rejectPushExecutor(),"rejectPush");
    }

}
