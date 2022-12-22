package com.zzh.juc.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 通过lock实现线程间通信
 * @Author: zzh
 * @Crete 2022/12/20 22:29
 */
public class Thread2 {

    private Integer num=0;

    private final Lock lock=new ReentrantLock();

    private final Condition condition=lock.newCondition();

    public void add() throws InterruptedException {
        lock.lock();
        try {
            while (num!=0){
                condition.await();
            }
            num++;
            System.out.println("num="+num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }


    public void desc() throws InterruptedException {
        lock.lock();
        try {
            while (num!=1){
                condition.await();
            }
            num--;
            System.out.println("num="+num);
            //singleAll：代替notifyAll()
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    /**
     * @Description:
     * @Author: zzh
     * @Crete 2022/12/20 22:34
     */
    public static class Juc2 {

        public static void main(String[] args) {
            Thread2 thread2 = new Thread2();

            new Thread(() -> {
                for (int i = 0; i < 50; i++) {
                    try {
                        thread2.add();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, "addThread1").start();
            new Thread(() -> {
                for (int i = 0; i < 50; i++) {
                    try {
                        thread2.add();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, "addThread2").start();
            new Thread(() -> {
                for (int i = 0; i < 50; i++) {
                    try {
                        thread2.desc();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, "descThread1").start();

            new Thread(() -> {
                for (int i = 0; i < 50; i++) {
                    try {
                        thread2.desc();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, "descThread2").start();

        }
    }
}
