package com.zzh.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: ReentrantLock()
 * @Author: zzh
 * @Crete 2023/1/10 20:51
 */
public class Aqs2 {


    ///
    public static void main(String[] args) {

        //公平锁和非公平锁的区别：抢占锁的时候，判断当前队列是否有排队的线程
        // 非公平锁，直接cas抢占， 公平锁会先判断一下队列中是否与线程在排队
        Lock lock=new ReentrantLock();
        Lock lock1=new ReentrantLock(true);

        //非公平锁
        lock.lock();
        lock.unlock();

        // 公平锁
        lock1.lock();
        lock1.unlock();

    }
}
