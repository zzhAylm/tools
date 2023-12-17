package com.zzh;

import com.zzh.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/12/14 15:58
 */
public class TestApplicationStr {

    @Test
    public void testStr() {
        // 返回 IOC 容器，基于 XML配置，传入配置文件的位置
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("test.xml");
        User user = (User) applicationContext.getBean("user");
        System.out.println("user = " + user);
    }

    @Test
    public void beans(){
        ApplicationContext context = new AnnotationConfigApplicationContext();
        // 查看基于注解的 IOC容器中所有组件名称
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.stream(beanNames).forEach(System.out::println);
    }
}
