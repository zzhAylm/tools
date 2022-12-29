package com.zzh.lock;

import java.util.concurrent.TimeUnit;

/**
 * @Description: LockSupport: AQS,线程中断机制，volatile 关键字
 * @Author: zzh
 * @Crete 2022/12/29 13:58
 */
public class Lock4 {

    //线程中断机制：
    //interrupte():实例方法
    //interrupted()：静态方法，返回当前中断状态，并将中断标志位设置为false
    //isinterrupte() ：判断 中断状态

    //volatile：实现线程可见行
    private static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            while (true) {
                if (flag) {
                    System.out.println(Thread.currentThread().getName() + "发现标志位，停止线程的运行");
                    break;
                } else {
                    System.out.println(Thread.currentThread().getName() + ",程序继续执行");
                }
            }
        }, "A").start();

        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            flag = true;
            System.out.println(Thread.currentThread().getName() + "修改标志位，停止线程A");
        }, "B").start();

    }
}
