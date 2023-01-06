package com.zzh.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/4 20:28
 */
public class Cas1 {


    //原子操作，线程安全
    static AtomicInteger atomicInteger=new AtomicInteger(0);
    public static void main(String[] args) {


        int andSet = atomicInteger.getAndSet(1);

    }
}
