package com.zzh.threadlocal;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/7 20:49
 */
public class ThreadLocal3 {


    //threadLocal: 源码分析
    //
//    ThreadLocal
    //Thread
    //ThreadLocalMap

    //Thread 包含 ThreadLocal  ThreadLocal 里面有一个TheadLocalMap
    public static void main(String[] args) {

        Thread thread=new Thread();
        ThreadLocal<Integer> threadLocal=ThreadLocal.withInitial(()->0);

    }
}
