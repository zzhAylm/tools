package com.zzh.jvm;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/21 14:35
 */
public class Jvm1 {

    //jvm整体结构
    //堆，方法区，栈，本地方法栈，程序计数器，类文件，类装载器，执行引擎
    // 底层的操作系统只能执行机器指令
    // 高级语言-》 汇编语言-》 机器指令-》执行
    // 前端的编译器，将类文件编译成 .class 文件，执行引擎将 .class 文件 编译成机器指令，最后操作系统执行

    //架构模型： 栈指令架构，寄存器指令架构

    // 类加载器： 引导类（bootstrap class loader）加载器，应用类加载器，系统类加载器，自定义加载器

    // 薯虚拟机： hotSport jRocket，J9


    public static void main(String[] args) {

    }
}
