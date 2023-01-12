package com.zzh.readwrite;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/11 21:55
 */
public class ReadWriteLock4 {

    //读写锁，锁降级，读锁降为写锁，写权限高于读的权限
    //写锁的降级，降级成为了读锁： 同一个线程，持有写锁，在没有释放写锁的情况下，他可以继续获取读锁，这就是写锁的降级，降级为读锁
    // 现获取写锁，然后在获取读锁，在释放写锁的次序，如果释放了写锁，那么就是完全转为读锁了
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    // 锁降级，只能是读锁降级位读锁
    //只能降级，不能升级
    public static void main(String[] args) {


        new Thread(() ->{
            readWriteLock.writeLock().lock();

            try {
                System.out.println(Thread.currentThread().getName()+"获取写锁");
                TimeUnit.SECONDS.sleep(1);


                readWriteLock.readLock().lock();
                System.out.println(Thread.currentThread().getName()+"获取读锁");

                readWriteLock.writeLock().unlock();
                System.out.println(Thread.currentThread().getName()+"写锁完成");


                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"读锁完成");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }finally {
                readWriteLock.readLock().lock();
            }

        },"A").start();

    }


    public static void write(){
        readWriteLock.writeLock().lock();


    }
}
