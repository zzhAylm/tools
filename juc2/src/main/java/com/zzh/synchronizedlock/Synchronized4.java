package com.zzh.synchronizedlock;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 偏向锁
 * @Author: zzh
 * @Crete 2023/1/9 21:14
 */
public class Synchronized4 {

    // 关闭延迟 ,偏向锁会有4秒的延迟
    public static void main(String[] args) throws InterruptedException {

        TimeUnit.SECONDS.sleep(5);
        Object o=new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        new Thread(() ->{
            synchronized (o){
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        },"input thread name").start();



    }
}
