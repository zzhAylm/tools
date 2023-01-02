package com.zzh.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Description: lockSupport得使用
 * @Author: zzh
 * @Crete 2023/1/2 18:51
 */
public class Lock13 {

    //lockSupport: park()：检查时候有许可证，无则阻塞， unpark()：发放许可证
    // 先park 后 unpark() 都可以
    //先 unpark() 在park（）也不会阻塞线程
    //通行证最多只有一张，多次检查会阻塞线程
    //连续多次发送通行证，数量也不会增加
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            System.out.println("A is come in");
            LockSupport.park();
            System.out.println("A is over");
        }, "A");
        thread.start();

        TimeUnit.SECONDS.sleep(1);


        new Thread(() ->{
            System.out.println("B is come in");
            LockSupport.unpark(thread);
            System.out.println("B is over");
        },"B").start();

    }
}
