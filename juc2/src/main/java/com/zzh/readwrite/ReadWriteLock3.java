package com.zzh.readwrite;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/11 21:44
 */
public class ReadWriteLock3 {

    //并发互斥性下降
    static Lock lock = new ReentrantLock();
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //读线程，先完成，才能进行写锁，读锁没有完成的额时候，写锁不能进行
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                try {
                    read1();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            },"A"+ i).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                try {
                    write1();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            },"B"+ i).start();
        }
    }

    public static void write() throws InterruptedException {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "发生写锁");
            TimeUnit.MILLISECONDS.sleep(2);
        } finally {
            System.out.println(Thread.currentThread().getName() + "结束写锁");
            lock.unlock();
        }
    }

    public static void read() throws InterruptedException {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "发生读锁");
            TimeUnit.SECONDS.sleep(1);
        } finally {
            System.out.println(Thread.currentThread().getName() + "结束读锁");
            lock.unlock();
        }
    }


    public static void write1() throws InterruptedException {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "发生写锁");
            TimeUnit.MILLISECONDS.sleep(2);
        } finally {
            System.out.println(Thread.currentThread().getName() + "结束写锁");
            readWriteLock.writeLock().unlock();
        }
    }

    public static void read1() throws InterruptedException {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "发生读锁");
            TimeUnit.SECONDS.sleep(1);
        } finally {
            System.out.println(Thread.currentThread().getName() + "结束读锁");
            readWriteLock.readLock().unlock();
        }
    }
}
