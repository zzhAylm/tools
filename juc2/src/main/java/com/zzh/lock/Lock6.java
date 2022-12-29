package com.zzh.lock;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/29 20:53
 */
public class Lock6 {

    //lock():修改其他线程的中断标志位
    // interrupt():实例方法，将线程设置为中断状态
    // interrupted():静态方法，返回线程的中断状态，并清楚自己的中断状态
    // isinterrupted():实例方法，判断自己的是否为中断状态
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                //如果中断标志位标记为true，停止程序的执行，中断线程
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("停止执行");
                    break;
                } else {
                    System.out.println("继续执行");
                }
            }
        }, "A");
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        //设置t1线程的中断标志位为true，其他线程设置，
        new Thread(t1::interrupt,"B").start();
        new Thread(() ->{
            int i=0;
            while (true){
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("停止运行");
                    break;
                }else {
                    System.out.println("继续执行");
                    if (i++>100) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        },"B").start();
    }
}
