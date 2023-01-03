package com.zzh.volatiles;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/3 21:43
 */
public class Volatile4 {

    //volatile关键字的使用场景：
    // 通过关键字 flag 实现线程间通信
    // 读写锁 读的时候使用volatile就可以，写的时候加锁
    // volatile：实现线程间的数据 可见性
    // DSL 单例模式（双重检查锁单例模式）

    private volatile static Volatile4 volatile4;

    private Volatile4() {
    }

    public static void main(String[] args) {


    }


    public static Volatile4 getInstance() {

        if (volatile4 == null) {

            synchronized (Volatile4.class) {
                if (volatile4 == null) {
                    volatile4 = new Volatile4();
                }
            }

        }
        return volatile4;

    }
}
