package com.zzh.cas;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/6 19:54
 */
public class Cas11 {

    //LongAdder：的使用案例
    //：LongAdders,AtomicLong: 在高竞争的情况下，LongAdder 性能更好
    public static void main(String[] args) {
        //只能进行简单的增加操作，不能进行乘除等复杂的运算
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.increment();
        System.out.println(longAdder.sum());

        //负责运算的操作，并设置初始值，Accumulator：累加器
        LongAccumulator accumulator=new LongAccumulator((left,right)->left+right,0);
        //0+1
        accumulator.accumulate(1);
        //1+3
        accumulator.accumulate(3);

        System.out.printf("%s", accumulator.get());
    }
}
