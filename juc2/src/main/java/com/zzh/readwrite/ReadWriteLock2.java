package com.zzh.readwrite;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/11 21:03
 */
public class ReadWriteLock2 {

    //
    public static void main(String[] args) {

        //可重入锁
        ReentrantLock reentrantLock = new ReentrantLock();

        //读写锁，读读共享，读写互斥，读读互斥
        //
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        //

        //邮戳锁：版本锁
        StampedLock stampedLock=new StampedLock();


        /**
         *
         * 无锁 ： 数据不一致
         * 有锁： synchronized，Lock 一个一个的执行，效率低
         * 读写锁： ReentrantReadWriteLock ,允许读读共享，多线程并发访问，
         *  缺点：写锁饥饿问题，锁降级
         *   写锁特别多，写锁没发获取，造成写锁饥饿问题
         * 邮戳锁：StampedLock
         * */


    }
}
