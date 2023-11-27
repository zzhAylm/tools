package com.zzh.spring.listener;

import com.zzh.spring.event.VoiceEvent;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.Duration;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/11/9 12:50
 */
public class MySpringApplicationRunListener implements SpringApplicationRunListener {

    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
//        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) context.getParentBeanFactory();
//        defaultListableBeanFactory.registerBeanDefinition("nane", new RootBeanDefinition(VoiceEvent.class));

        DefaultListableBeanFactory beanFactory =(DefaultListableBeanFactory) context.getBeanFactory();
//        beanFactory.registerCustomEditor();
        beanFactory.registerBeanDefinition("test", new RootBeanDefinition(VoiceEvent.class));
        SpringApplicationRunListener.super.started(context, timeTaken);
    }
}
