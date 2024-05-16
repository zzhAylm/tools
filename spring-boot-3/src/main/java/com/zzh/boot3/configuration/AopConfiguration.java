package com.zzh.boot3.configuration;

import com.zzh.boot3.aop.Annoncationadvisor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/3/29 14:43
 */
@Configuration
public class AopConfiguration {


    @Value("${spring.data.redis.host}")
    private String host;

    @Bean
    public Annoncationadvisor annoncationadvisor() {
        Pointcut pointcut = new AnnotationMatchingPointcut(Bean.class);
        Pointcut pointcut1 = new NameMatchMethodPointcut();
        return new Annoncationadvisor(pointcut, new MethodInterceptor() {
            @Nullable
            @Override
            public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
                return invocation.proceed();
            }
        });
    }
}
