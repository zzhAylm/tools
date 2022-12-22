package com.zzh.juc.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description: CyclicBarrier ,栅栏
 * @Author: zzh
 * @Crete 2022/12/22 01:08
 */
public class Thread10 {

    //设置一个初始值，只有当等待的线程到达这个数量之后，这几个等待的线程才会继续往下还行，否则会一直等待
    //如果到达栅栏的数量一直都为打到设置的初始值，所有正在等待的线程会一直等待
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{
            System.out.println("等待的线程数量到达7个，放行所有等待的线程，继续执行");
        });

        for (int i = 0; i < 7; i++) {
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName()+"线程收到达了栏杆，开始等待放行");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("等待结束，"+Thread.currentThread().getName()+"线程继续执行");
            },String.valueOf(i)).start();
        }

    }
}
