package com.zzh.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/26 18:00
 */
public class Lock1 {
    private static Integer num = 100;
    static Lock lock = new ReentrantLock(true);

    //悲观锁，乐观锁
    //类锁，对象锁，无锁
    public static void main(String[] args) {

        //如果没有解锁，线程可以结束，但是其他线程无法获取到这个锁了
        new Thread(() ->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"get lock,in");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName()+"get lock,in in");
                }finally {
                    lock.unlock();
                }
            }finally {
                lock.unlock();
            }
        },"input thread name").start();


    }
}
