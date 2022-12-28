package com.zzh.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/28 21:16
 */
public class Lock2 {

    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    //同步代码快，object1，object2
    //检测死锁，
    //1。通过jps获取到进程号
    //2。通过进程号 jstack 进程好，查看进程的详细信息，来观察是否发生了死锁
    Object object1=new Object();
    Object object2=new Object();

    //死锁
    public static void main(String[] args) {

        new Thread(() -> {
            lock1.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"获取到lock1，获取lock2");
                Thread.sleep(1000);
                lock2.lock();
                try {
                    System.out.println("获取到locak2，成功执行");
                }finally {
                    lock2.unlock();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock1.unlock();
            }

        }, "input thread name").start();


    }
}
