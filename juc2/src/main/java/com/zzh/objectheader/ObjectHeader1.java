package com.zzh.objectheader;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/8 20:20
 */
public class ObjectHeader1 {

    //Java 对象内存布局和对象头
    // JUC和JVM同步锁机制，
    // CAS ：自旋锁，是获取不到锁就一直自旋锁，
    // cas和synchronized： 的区别
    public static void main(String[] args) {


        Lock lock=new ReentrantLock();


    }
}
