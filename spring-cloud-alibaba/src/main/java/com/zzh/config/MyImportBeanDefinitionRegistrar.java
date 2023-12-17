package com.zzh.config;

import com.zzh.domain.Banana;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

@ComponentScan
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        final String beanName = "Banana";
        boolean contain = registry.containsBeanDefinition(beanName);
        if (!contain) {
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Banana.class);
            registry.registerBeanDefinition(beanName, rootBeanDefinition);
        }
    }
}
