package com.zzh.cas;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Description: 数组类型，原子类：AtomicIntegerArray,AtomicLongArray,AtomicReferenceArray
 * @Author: zzh
 * @Crete 2023/1/5 21:25
 */
public class Cas5 {

    //cas：
    public static void main(String[] args) {

        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);


        AtomicIntegerArray array = new AtomicIntegerArray(new int[5]);

        System.out.println(array.getAndIncrement(0));
        System.out.println(array.getAndIncrement(0));



    }
}
