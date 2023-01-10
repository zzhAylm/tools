package com.zzh.synchronizedlock;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Description: 无锁
 * @Author: zzh
 * @Crete 2023/1/9 00:46
 */
public class Synchronized2 {

    //无锁：
    public static void main(String[] args) {

        //打印对象的布局信息： 对象头，实例数据，对齐填充
        // 对象标记，类型指针，
        // 前25位：unused
        // 31位 hashcode
        // 1 位：unused
        // 4 位： 对象分代年龄
        // 3 位：锁的种类 001：无锁，101：偏向锁，00：轻量级锁，10：重量级锁

        Object o = new Object();
        System.out.println("hascode"+o.hashCode());
        System.out.println("16进制"+Integer.toHexString(o.hashCode()));
        System.out.println("2进制"+Integer.toBinaryString(o.hashCode()));
        System.out.println(ClassLayout.parseInstance(o).toPrintable());


    }
}
