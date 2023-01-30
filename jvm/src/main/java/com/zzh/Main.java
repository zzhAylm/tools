package com.zzh;

/**
 * @Description:
 * @Author: zzh
 * @Crete ${DATE} ${TIME}
 */
public class Main {

    // bootstrap classloader
    // 扩转类加载器
    // 系统类加载器
    // 用户自定义加载器
    // 只要是继承classloader类的加载器都是用户自定义加载【总体上分为用户自定义加载器和bootstrap classloader】

    public static void main(String[] args) {
        System.out.println("Hello world!");

        //系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

        //扩转类加载器
        ClassLoader parent = systemClassLoader.getParent();


        //bootstrap classloader : 无法获取
        ClassLoader parent1 = parent.getParent();

        // 用户自定义类加载器

    }
}
