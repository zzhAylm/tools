package com.zzh.readwrite;

import java.util.concurrent.locks.StampedLock;

/**
 * @Description: StampedLock锁，邮戳锁，票据锁
 * @Author: zzh
 * @Crete 2023/1/11 22:38
 */
public class ReadWriteLock6 {


    //有没有比读写锁更快的锁？

    //StampedLock锁
    //读写锁，饥饿问题，1000线程999个读锁，造成读锁饥饿问题
    /**
     * 读写锁：读读共享 ， 读的时候会阻塞写线程（造成读线程饥饿问题）
     *
     * StampedLock: 读的时候，允许写锁的获取，但读锁是需要对结果进行校验
     *
     *
     * **/

    static StampedLock stampedLock=new StampedLock();

    public static void main(String[] args) {

        stampedLock.tryOptimisticRead();

    }
}
