package com.zzh.threadlocal;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/7 19:00
 */
public class ThreadLocal1 {

    //
    //
    ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal<>();

    static ThreadLocal<Integer> threadLocal1 = ThreadLocal.withInitial(() -> 0);


    static AtomicLong atomicLong=new AtomicLong(0);
    ThreadLocal<Integer> threadLocal2 = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) throws InterruptedException {


        CountDownLatch countDownLatch=new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Random random = new Random();
                try {
                    for (int j = 0; j < random.nextInt(5)+1; j++) {
                        sale();
                        atomicLong.getAndAdd(1);
                    }
                } finally {
                    countDownLatch.countDown();
                    System.out.printf(Thread.currentThread().getName()+"卖出%s\n", threadLocal1.get());

                    //清理线程内存，如果是线程池的场景，线程是不回收的状态，如果变量不删除，容易造成内存泄漏
                    //如果不删除容易造成后线程的数据不正确
                    threadLocal1.remove();
                }

            }, String.valueOf(i)).start();
        }
        countDownLatch.await();

        System.out.println("一种卖出："+atomicLong.get());

    }

    public static void sale() {
        threadLocal1.set(threadLocal1.get() + 1);
    }

}
