package com.zzh.juc.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 线程间的定制化通讯
 * @Author: zzh
 * @Crete 2022/12/20 22:45
 */
public class Thread3 {

    private final Lock lock = new ReentrantLock();

    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private final Condition condition3 = lock.newCondition();

    private String single = "A";

    private Integer loop = 1;

    public Thread3(Integer loop) {
        this.loop = loop;
    }

    public void print5() throws InterruptedException {

        for (int i = 0; i < loop; i++) {
            lock.lock();
            try {
                while (!single.equals("A")) {
                    condition1.await();
                }
                for (int j = 0; j < 5; j++) {
                    System.out.println("A");
                }
                single = "B";
                condition2.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public void print10() throws InterruptedException {

        for (int i = 0; i < loop; i++) {
            lock.lock();
            try {
                while (!single.equals("B")) {
                    condition2.await();
                }
                for (int j = 0; j < 10; j++) {
                    System.out.println("B");
                }
                single = "C";
                condition3.signal();
            } finally {
                lock.unlock();
            }
        }

    }

    public void print15() throws InterruptedException {
        for (int i = 0; i < loop; i++) {
            lock.lock();
            try {
                while (!single.equals("C")) {
                    condition3.await();
                }
                for (int j = 0; j < 15; j++) {
                    System.out.println("C");
                }
                single = "A";
                condition1.signal();
            } finally {
                lock.unlock();
            }
        }
    }


    /**
     * @Description: 定制化的线程间通信 , lock ->condition1,condition2,condition3
     * 同一把锁，实现三个线程间的协调通信
     * @Author: zzh
     * @Crete 2022/12/21 00:07
     */
    public static class Juc3 {

        public static void main(String[] args) {

            Thread3 thread3 = new Thread3(10);
            new Thread(() -> {
                try {
                    thread3.print5();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "threadA").start();

            new Thread(() -> {
                try {
                    thread3.print10();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "threadB").start();
            new Thread(() -> {
                try {
                    thread3.print15();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "threadC").start();
        }
    }
}
