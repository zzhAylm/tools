package com.zzh.lock;

/**
 * @Description: Thread.interrupted()
 * @Author: zzh
 * @Crete 2022/12/29 21:59
 */
public class Lock9 {

    //静态的中断方法
    //返回当前的中断标志位的值，并将当前的中断状态设置为false
    //Thread.interrupted():放回当前的标志位，并将标志位清楚，设置位false
    //interrupt()：将标志位设置为true
    //isinterrupt():返回当前的标志为
    public static void main(String[] args) {

         new Thread(() ->{

             System.out.printf("当前线程的标志为%s\n", Thread.interrupted());

             Thread.currentThread().interrupt();
             System.out.printf("当前线程的标志为%s\n", Thread.interrupted());
             System.out.printf("当前线程的标志为%s\n", Thread.interrupted());

         },"A").start();


    }
}
