package com.zzh.boot3.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class Boot3BeanInstantiationPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if ("demoController".equals(beanName)) {
            System.out.println("post process before " + beanName + " instantiation");
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if ("demoController".equals(beanName)) {
            System.out.println("post process after " + beanName + " instantiation");
        }
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if ("demoController".equals(beanName)) {
            System.out.println("post process " + beanName + " properties");
        }
        return pvs;
    }
}
