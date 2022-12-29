package com.zzh.lock;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/29 21:06
 */
public class Lock7 {
    //协商机制，只是将线程的中断标志为修改为true或者false，至于线程是否中断停止，由自己决定
    //native：调用底层的c语言的函数库
    //调用线程的interrupt（）方法时，如果线程正常活动，那么线程的标志为别设置为true，仅此而已，如果想要线程停止还需要线程自己进程配合
    //interrupt（）被调用时，如果线程处于 wait，sleep，join状态，线程会理解退出阻塞状态，并抛出InterruptedException（）异常
    //当线程完成执行后，不活动的线程不会产生任务影响，返回的线程状态为 false
    public static void main(String[] args) throws InterruptedException {
//        interrupted()

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("计数" + i);
            }
            //true
            System.out.println("线程的中断状态：" + Thread.currentThread().isInterrupted());
        }, "A");
        t1.start();
        System.out.println("线程的中断状态：" + t1.isInterrupted());//false
        TimeUnit.MILLISECONDS.sleep(2);
        t1.interrupt();
        System.out.println("线程的中断状态：" + t1.isInterrupted());//true

        TimeUnit.SECONDS.sleep(5);

        System.out.println("线程的中断状态：" + t1.isInterrupted());//false，线程已经终止，返回false
    }
}
