package com.zzh.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Description: 原子类
 * @Author: zzh
 * @Crete 2022/12/29 20:41
 */
public class Lock5 {

    //线程安全的原子类,线程之间修改可以保证线程安全，不要直接去停止别人的线程，要
    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {

            while (true) {
                if (atomicBoolean.get()) {
                    System.out.println("停止执行");
                    break;
                } else {
                    System.out.println("继续执行");
                }
            }
        }, "A").start();

        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> atomicBoolean.set(true), "B").start();

    }
}
