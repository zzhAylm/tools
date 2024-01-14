package com.zzh.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(MyBeanFactoryPostProcessor.class);

    public MyBeanFactoryPostProcessor() {
        logger.info("实例化MyBeanFactoryPostProcessor Bean");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        int beanDefinitionCount = beanFactory.getBeanDefinitionCount();
        Object rejectPushExecutor = beanFactory.getBean("rejectPushExecutor");
        logger.info("Bean定义个数: " + beanDefinitionCount);
    }

    @Component
    static class TestBean {
        public TestBean() {
            logger.info("实例化TestBean");
        }
    }
}
