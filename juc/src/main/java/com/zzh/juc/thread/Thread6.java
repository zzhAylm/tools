package com.zzh.juc.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 锁的八个问题
 * @Author: zzh
 * @Crete 2022/12/21 22:46
 */
public class Thread6 {

    //锁：对象锁和类锁
    // 锁的是对象锁，整个对象的其他同步方法，都需要获取实例锁
//    public synchronized void lock(){
//
//    }

    //锁的是整个类的锁，整个类所有的静态同步方法都需要等待这把锁，放开之后才可以使用
//    public static synchronized void lock(){
//
//    }

    // 可重入锁；就是一个线程不用释放，可以重复的获取一个锁n次，只是在释放的时候，
    // 也需要相应的释放n次。（简单来说：A线程在某上下文中或得了某锁，当A线程想要在次获取该锁时，
    // 不会应为锁已经被自己占用，而需要先等到锁的释放）假使A线程即获得了锁，又在等待锁的释放，
    // 就会造成死锁
    //同一把锁可以被一个线程多次获取，多次获取的如同，不用释放，就可以再次获取
    //synchronized 和 ReentrantLock 都是可重入锁
    public static void main(String[] args) {

        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + "外层");
                synchronized (o) {
                    System.out.println(Thread.currentThread().getName() + "中层");
                    synchronized (o) {
                        System.out.println(Thread.currentThread().getName() + "内层");
                    }
                }
            }
        }, "a").start();


        Lock lock = new ReentrantLock();

        new Thread(() -> {

            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "外层");

                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "中层");
                    lock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + "内层");
                    } finally {
                        lock.unlock();
                    }
                } finally {
                    lock.unlock();
                }

            } finally {
                lock.unlock();
            }
        }, "B").start();
    }
}
