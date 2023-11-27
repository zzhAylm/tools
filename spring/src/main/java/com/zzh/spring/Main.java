package com.zzh.spring;

import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/6/27 20:00
 */
public class Main {

//    @Bean
    public static void main(String[] args) throws IOException {
//        Bean1 bean1 = new Bean1();
//        System.out.println(bean1);

        MediaType mediaType = MediaType.valueOf("application/json;charset=UTF-8");
        System.out.println(MediaType.APPLICATION_JSON.includes(mediaType));


        ResourceLoader resourceLoader=new DefaultResourceLoader();

        Resource resource = resourceLoader.getResource("bootstrap.yml");

        PropertySourceLoader propertySourceLoader=new YamlPropertySourceLoader();

        List<PropertySource<?>> load = propertySourceLoader.load("bootstrap.yml", resource);

       load.stream().forEach(propertySource -> System.out.println(propertySource.toString()));
    }
}
