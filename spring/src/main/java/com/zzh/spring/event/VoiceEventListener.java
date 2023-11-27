package com.zzh.spring.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/3/20 21:00
 */
@Component
public class VoiceEventListener implements ApplicationListener<VoiceEvent> {

    @Autowired
    private ApplicationContext applicationContext;


    @Override
    public void onApplicationEvent(VoiceEvent voiceEvent) {
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getParentBeanFactory();
//        applicationContext.getParentBeanFactory().
        defaultListableBeanFactory.registerBeanDefinition("nane", new RootBeanDefinition(VoiceEvent.class));
        // 监听并处理事件
        System.out.println(voiceEvent.toString());

        new BeanDefinitionRegistryPostProcessor() {
            @Override
            public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
            }
            @Override
            public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
                registry.registerBeanDefinition("name1", new RootBeanDefinition(VoiceEvent.class));
            }
        };
    }
}
