package com.zzh.boot3.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/14 18:26
 */
@Configuration
public class SpringBoot3Configuration implements WebMvcConfigurer {


    // WebMvcAutoConfiguration
    /**
     * @Configuration(proxyBeanMethods = false)
     * @ConditionalOnWebApplication(type = Type.SERVLET)
     * @ConditionalOnClass({ Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class })
     * @ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
     * @AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)
     * @AutoConfigureAfter({ DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class,
     * 		ValidationAutoConfiguration.class })
     * */

    // WebMvcConfigurer
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/index.html");
        registry.addViewController("/login").setViewName("/index.html");
        registry.addViewController("/notFound").setViewName("/index.html");
        registry.addViewController("/index").setViewName("/index.html");
    }


//     yml 处理
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YmlHttpMessageConverter());
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.extendMessageConverters(converters);
    }
}
