package com.zzh.cas;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Description: 引用类型原子类：AtomicReference,AtomicStampedReference,AtomicMarkableReference
 * @Author: zzh
 * @Crete 2023/1/5 21:33
 */
public class Cas6 {


    public static void main(String[] args) {

        AtomicReference<Book> atomicReference = new AtomicReference<>();

//       底层的： Unsafe
        //缺点：自旋锁，循环消耗，ABA问题
        //AtomicMarkableReference :解决是否修改过 ，标识位（true:被修改过，false：没有修改过）
        //  AtomicStampedReference :解决修改过几次，版本号（version：修改一次版本好加1）
//        AtomicMarkableReference
    }


    class Book {

    }
}
