package com.zzh.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/2 18:30
 */
public class Lock12 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() ->{
            lock.lock();
            try {
                System.out.println("A is come in");
                condition.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }finally {
                System.out.println("A is over");
                lock.unlock();
            }
        },"A").start();

        new Thread(() ->{
            lock.lock();
            try {
                System.out.println("B is come in");
                condition.signalAll();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }finally {
                System.out.println("B is over");
                lock.unlock();
            }
        },"B").start();
    }
}
