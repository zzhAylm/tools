package com.zzh.boot3.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/3/29 14:40
 */
public class Annoncationadvisor extends AbstractPointcutAdvisor {

    private Pointcut pointcut;

    private Advice advice;

    public Annoncationadvisor(Pointcut pointcut, Advice advice) {
        this.pointcut = pointcut;
        this.advice = advice;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

}
