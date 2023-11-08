package com.zzh.jdk8;

import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/10/31 21:14
 */
public class Stream2 {

    public static void main(String[] args) {
//        上面都是串行 Stream 的实例。并行 parallelStream 在使用方法上和串行一样。主要区别是 parallelStream 可多线程执行，是基于 ForkJoin 框架实现的，有时间大家可以了解一下 ForkJoin 框架和 ForkJoinPool。这里可以简单的理解它是通过线程池来实现的，这样就会涉及到线程安全，线程消耗等问题。下面我们通过代码来体验一下并行流的多线程执行。

        List<Integer> numbers = Arrays.asList(1, 2, 5, 4);
        numbers.parallelStream().forEach(num -> System.out.println(Thread.currentThread().getName() + ">>" + num));
    }
//    ForkJoinPool.commonPool-worker-2>>4
//    ForkJoinPool.commonPool-worker-11>>1
//    ForkJoinPool.commonPool-worker-9>>2
}
