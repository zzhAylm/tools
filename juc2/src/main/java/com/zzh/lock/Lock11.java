package com.zzh.lock;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/2 18:07
 */
public class Lock11 {

    //使用 wait() 和notify(): 必须持有锁  synchronized(锁对象)： 必须持有锁才能进行操作
    public static void main(String[] args) throws InterruptedException {

        Object o = new Object();

        new Thread(() -> {
            synchronized (o) {
                System.out.println("A is come in");
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("A is over");
            }

        }, "A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            synchronized (o) {
                System.out.println("B is come in");
                o.notifyAll();
                System.out.println("B is over,notify A");
            }
        }, "B").start();


    }


}
