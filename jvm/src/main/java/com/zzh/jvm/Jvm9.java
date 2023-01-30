package com.zzh.jvm;

/**
 * @Description: 程序计数器（pc寄存器）
 * @Author: zzh
 * @Crete 2023/1/29 13:44
 */
public class Jvm9 {

    // 线程私有的
    //每个线程都一个程序计数器，用来存储下一条指令的地址，行号指示器
    // 不会发生gc和oom情况
    // 程序计数器，cpu在多个线程之间切换，当cpu执行当前线程是，可以通过程序计数器获取地址指令，来确定改执行那条指令了
    // 并行 vs 串行
    // 并发：一核在多个线程之间并发执行
    public static void main(String[] args) {
        int i=10;
        int j=20;
        int k=i+j;
        String s="zzh";
        System.out.println(i);
        System.out.println(k);
    }
}
