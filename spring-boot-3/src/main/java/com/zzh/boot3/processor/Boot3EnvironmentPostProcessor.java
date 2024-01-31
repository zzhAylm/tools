package com.zzh.boot3.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/31 15:42
 */
@Component
public class Boot3EnvironmentPostProcessor implements EnvironmentPostProcessor {


    private static final String CONFIG_NAME = "boot3.yml";

    private static final String CONFIG_PAH = "/META-INF/config/boot3.yml";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        try {
            YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();

            MutablePropertySources propertySources = environment.getPropertySources();
            List<PropertySource<?>> propertySourceList = yamlPropertySourceLoader.load(CONFIG_NAME, new ClassPathResource(CONFIG_PAH));
            if (propertySourceList == null || propertySourceList.isEmpty()) {
                return;
            }
            for (PropertySource propertySource : propertySourceList) {
                propertySources.addLast(propertySource);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
