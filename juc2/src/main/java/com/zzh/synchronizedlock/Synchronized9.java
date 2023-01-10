package com.zzh.synchronizedlock;

/**
 * @Description: 锁总结
 * @Author: zzh
 * @Crete 2023/1/10 10:32
 */
public class Synchronized9 {
    // 无锁   001
    // 偏向锁：对象头中Mark word标记位指向线程id 101
    // 轻量级锁： 指向 线程栈中对象头中的Mark word（存在与栈内存，每个线程的栈中都存在一个Mark word区域） 00
    // 重量级锁： 指向 堆中Monitor 对象，monitor对象中的owner指向线程  10

    // 每个对象都是一把锁：每个对象都指向 一个ObjectMonitor 对象 ，ObjectMonitor 存储线程信息
    // 偏向锁和hascode无法共存
    // 对象标记：64位，无法全部保存 偏向线程id和hascode

    // 锁消除：,每个线程一把锁，没有锁的作用
    public void synchronizedMethod() {
        Object o = new Object();
        synchronized (o) {
            System.out.println(o.hashCode());
        }
    }

    //锁粗化: 将四个锁中的内容和并，防止多次获取锁释放锁的过程
    public void synchronizedMethod1() {
        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                System.out.println("11111111");
            }
            synchronized (o) {
                System.out.println("22222222");
            }
            synchronized (o) {
                System.out.println("3333333");
            }
            synchronized (o) {
                System.out.println("444444");
            }
            synchronized (o) {
                System.out.println("5555555");
            }
        }, "input thread name").start();
    }

    public static void main(String[] args) {


    }
}
