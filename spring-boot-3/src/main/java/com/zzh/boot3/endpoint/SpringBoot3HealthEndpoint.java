package com.zzh.boot3.endpoint;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/26 21:47
 */
@Component
public class    SpringBoot3HealthEndpoint extends AbstractHealthIndicator {


    /**
     * 健康检查
     * */
    @Override
    protected void doHealthCheck(Health.Builder builder) {
        builder.down().withDetail("key", "value").build();
    }
}

