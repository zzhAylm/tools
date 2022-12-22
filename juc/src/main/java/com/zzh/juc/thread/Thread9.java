package com.zzh.juc.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: CountDownLatch
 * @Author: zzh
 * @Crete 2022/12/22 00:50
 */
public class Thread9 {

    //countDownLatch: 计数器，多个线程需要一共处理的数据量
    //当自线程处理一条，countDownLatch 减1，知道数据量都处理完之后
    //祝线程才会继续往下执行，否则就是在await()方法卡住
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch=new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() ->{
                try {
                    System.out.println(Thread.currentThread().getName()+"执行任务");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }finally {
                    countDownLatch.countDown();
                }
            },String.valueOf(i)).start();
        }

        countDownLatch.await();

        System.out.println("主线程完成");
    }
}
