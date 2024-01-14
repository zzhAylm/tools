package com.zzh.websocket.proxy;

import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/13 15:46
 */
@Component
public class AspectProperties {


    private static Object target;


    public static void main(String[] args) {
        ClassLoader classLoader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        Object proxyInstance = Proxy.newProxyInstance(classLoader, interfaces, (proxy, method, args1) -> method.invoke(proxy, args1));


    }
}
