package com.zzh.jvm;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/29 10:31
 */
public class Jvm5 {

    //
    public static void main(String[] args) throws ClassNotFoundException {

        ClassLoader classLoader = Class.forName("java.lang.String").getClassLoader();
        // 上下文类加载器，应用程序类加载器
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

        ClassLoader extensionClassLoader = systemClassLoader.getParent();


    }
}
