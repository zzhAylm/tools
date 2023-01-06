package com.zzh.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/4 21:39
 */
public class Cas2 {

    //自旋锁：
    //自旋锁的好处：减少线程的上下文切换的开销
    // 但是自旋锁也会消耗一部分cpu（循环）
    //自旋锁的实现
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public static void main(String[] args) throws InterruptedException {
        Cas2 cas2 = new Cas2();

        new Thread(() -> {
            cas2.lock();

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cas2.unlock();
        }, "input thread name").start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            cas2.lock();
            cas2.unlock();
        }, "B").start();
    }

    public void lock() {
        System.out.println(Thread.currentThread() + "come in!");
        while (!atomicReference.compareAndSet(null, Thread.currentThread())) {

        }
    }

    public void unlock() {

        System.out.println(Thread.currentThread() + "come in!");
        while (!atomicReference.compareAndSet(Thread.currentThread(), null)) {

        }

    }

}
