package com.zzh.threadlocal;

import java.lang.ref.SoftReference;

/**
 * @Description: 软引用
 * @Author: zzh
 * @Crete 2023/1/7 23:43
 */
public class ThreadLocal5 {
    //当内存够的时候，不会被回收
    //当内存不够的时候，会执行回收操作
    public static void main(String[] args) {

        SoftReference<ThreadLocal4.MyObject> softReference=new SoftReference<>(new ThreadLocal4.MyObject());

        System.out.println("gc before,myObject"+softReference.get());
        //操作，是内存不够用，如果内存不够用了，gc会回收软引用，如果内存够用，则不会回收软引用
        System.gc();


        System.out.println("gc after,myObject"+softReference.get());

    }

}
