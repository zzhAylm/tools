package com.zzh.threadlocal;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @Description: 虚引用
 * @Author: zzh
 * @Crete 2023/1/7 23:55
 */
public class ThreadLocal7 {

    //虚引用
    public static void main(String[] args) {

        //对象
        ThreadLocal4.MyObject myObject = new ThreadLocal4.MyObject();
        //队列
        ReferenceQueue<ThreadLocal4.MyObject> myObjectReferenceQueue = new ReferenceQueue<>();

        //虚引用创建
        //虚引用只是为了一种对象回收的时候的一种灵活的通知机制，虚引用被回收后会被加入到引用队列中，表示虚引用已经被回收了
        PhantomReference<ThreadLocal4.MyObject> phantomReference=new PhantomReference<>(myObject, myObjectReferenceQueue);

        //


    }
}
