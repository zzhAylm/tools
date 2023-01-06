package com.zzh.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description: 原子类之基本类型: AtomicInteger,AtomicBoolean,AtomicLong
 * @Author: zzh
 * @Crete 2023/1/5 09:54
 */
public class Cas4 {

    // AtomicStampedReference:加入版本号的原子引用类
    // AtomicStampedReference： 原子引用类

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger atomicInteger=new AtomicInteger();
        CountDownLatch countDownLatch=new CountDownLatch(50);
        for (int i = 0; i < 50; i++) {
            new Thread(() ->{
                try {
                    for (int j = 0; j < 1000; j++) {
                        atomicInteger.getAndIncrement();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            },String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println("最后结果："+atomicInteger.get());
    }
}
