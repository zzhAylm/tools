package com.zzh.jvm;

/**
 * @Description: 双亲委派机制
 * @Author: zzh
 * @Crete 2023/1/29 10:54
 */
public class Jvm6 {

    //  双亲委派机制：
    // 防止源码被污染，类加载重复
    // 父类加载器无法加载的时候，会委派给子类加载器进行加载

    // SPI 接口，
    // 防止类的重复加载
    // 防止核心API被篡改

    // 创建 java.lang.xxx class 属于启动类加载器服务，他会直接报错，我们不能在java.lang  包下随意创建类

    //
    public static void main(String[] args) {
        String str= "zzh";
        System.out.println("str="+str);
    }
}
