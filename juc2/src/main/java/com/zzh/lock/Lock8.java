package com.zzh.lock;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/29 21:41
 */
public class Lock8 {
    // 如果线程正在 进行 sleep wait join 等方法的时候，这是后调用线程的 interrupt()方法
    //线程的中断状态会被清楚，即inperrupt（）被设置为false，如果不在异常处再次中断线程，可能会导致线程一直执行
    //所以需要在 interruptException异常处，中断线程
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {

            while (true){
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("线程停止执行");
                    break;
                }else {
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException e) {
                        //线程在sleep，调用线程的中断interrput会使线程报错，
                        //跳出阻塞，并且清楚中断状态，可能会导致线程无法停止执行
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                    System.out.println("线程继续执行");
                }
            }
        }, "t1");
        t1.start();

        TimeUnit.SECONDS.sleep(1);
        new Thread(t1::interrupt,"B").start();
    }
}
