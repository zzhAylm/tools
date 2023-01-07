package com.zzh.cas;

import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/6 20:09
 */
public class Cas12 {
    public static Long number = 0L;

    //推荐使用 LongAdder 和 LongAccumulator
    // 在高并发的情况下，性能更好
    public static void main(String[] args) throws InterruptedException {
        LongAccumulator accumulator = new LongAccumulator(Long::sum, 0);
        LongAdder adder = new LongAdder();
        AtomicLong atomicLong = new AtomicLong();
        CountDownLatch countDownLatch = new CountDownLatch(30);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000000; j++) {
                        //                    atomicLong.getAndIncrement();
                        //                    adder.add(1);
                        accumulator.accumulate(1);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        stopWatch.stop();
//        System.out.printf("最终结果：%s\n", atomicLong.get());
//        System.out.printf("最终结果：%s\n", adder.sum());
        System.out.printf("最终结果：%s\n", accumulator.get());
        System.out.println("耗时：" + stopWatch.getTotalTimeSeconds());

        System.out.println("窗口 Cells 的数量是2的次幂");

    }


    //使用锁的线程安全的方式操作 number
    public static synchronized void add() {
        number++;
    }


    static AtomicLong atomicLong = new AtomicLong();

    public static void atomicLongAdd() {
        atomicLong.incrementAndGet();
    }

    static LongAdder adder=new LongAdder();
    public static void longAdder(){
        adder.add(1);
    }

     static LongAccumulator accumulator=new LongAccumulator(Long::sum, 0);
    public static void accumulatorAdd() {
        accumulator.accumulate(1);
    }
}
