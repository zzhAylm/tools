package com.zzh.juc.thread;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Description: semaphore :信号灯
 * @Author: zzh
 * @Crete 2022/12/22 01:16
 */
public class Thread11 {

    //六辆汽车，三个停车位，
    //安排那三个车停到停车位，也就是安排哪三个线程执行

    //countDownLatch，CyclicBarrier ，semaphore； 都是安排调度线程的工具
    public static void main(String[] args) {

        //三个信号量，只有拿到信号量的线程才可以继续执行
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() ->{
                try {
                    //获取信号量
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"号线程获取到信号量，继续执行");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName()+"号线程，执行完毕，释放信号量");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
