package com.zzh.threadlocal;

import java.lang.ref.WeakReference;

/**
 * @Description: 弱引用
 * @Author: zzh
 * @Crete 2023/1/7 23:52
 */
public class ThreadLocal6 {

    // 弱引用，只要发生gc，就会回收弱引用
    //
    public static void main(String[] args) {

        WeakReference<ThreadLocal4.MyObject> weakReference=new WeakReference<>(new ThreadLocal4.MyObject());

        System.out.println("before, myObject"+weakReference.get());

        System.gc();
        System.out.println("after, myObject"+weakReference.get());

    }
}
