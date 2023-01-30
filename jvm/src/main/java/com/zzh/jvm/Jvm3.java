package com.zzh.jvm;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/28 17:53
 */
public class Jvm3 {

    // 一个类只会被家在一次：  且类的初始化过程是线程安全的
    // 引导类加载器 bootstrap classloader
    // 扩转类加载器
    // 系统类加载器
    // 用户自定义加载器
    // 只要是继承classloader类的加载器都是用户自定义加载【总体上分为用户自定义加载器和bootstrap classloader】

    public static void main(String[] args) {
        System.out.println("Hello world!");

        //系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        //扩转类加载器
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);
        //bootstrap classloader : 无法获取
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);
        // 用户自定义类加载器
        // 对于自定义的类来说，默认使用系统类加载器进行家在
        System.out.printf("%s", Jvm3.class.getClassLoader());

        //String 类的类加载器，默认使用 引导类加载器（bootstrap classloader）
        // Java 的核心类库都是使用引导类加载器加载的，
        System.out.println(String.class.getClassLoader());
    }
}
