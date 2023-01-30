package com.zzh.jvm;

/**
 * @Description: 运行时数据区
 * @Author: zzh
 * @Crete 2023/1/29 11:44
 */
public class Jvm7 {

    // 类加载子系统
    // 运行时数据区： 堆，虚拟机栈，方法区，本地方法栈，程序计数器
    // 执行引擎，
    // gc

    // jdk8 之后，方法区换成了元空间，（使用本地内存），一般不会出现内存溢出（gc量占5%）
    // 堆是垃圾回去的主要操作空间：（gc 量战95%）
    public static void main(String[] args) {

        // 运行时数据区对象
        Runtime runtime = Runtime.getRuntime();




    }
}
