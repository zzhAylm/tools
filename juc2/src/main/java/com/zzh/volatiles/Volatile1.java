package com.zzh.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/3 19:49
 */
public class Volatile1 {

    //实现线程之间可见性 】
    static volatile Boolean flag =true;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            System.out.println("A is come in");
            while (flag) {
                System.out.println("A flag="+flag);
            }
            System.out.println("A is over,flag=" + flag);
        }, "A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            System.out.println("B is come in");
            flag=false;
            System.out.println("B is over,flag=" + flag);
        }, "B").start();
    }

}
