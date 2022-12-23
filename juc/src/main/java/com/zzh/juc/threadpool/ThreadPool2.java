package com.zzh.juc.threadpool;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/22 23:10
 */
public class ThreadPool2 {

    public static void main(String[] args) {


        /**
         * int corePoolSize, :核心线程数量
         * int maximumPoolSize, ：最大的线程数量
         * long keepAliveTime, ：超过核心线程数量的线程，存活时间， 超过这个时间没有处理业务会停掉该线程，恢复到核心线程数量
         * TimeUnit unit, ：存活时间单位
         * BlockingQueue<Runnable> workQueue, ：排队的队列
         * ThreadFactory threadFactory, ;线程工厂
         * RejectedExecutionHandler handler ： 拒绝策略
         * */
//        new ThreadPoolExecutor()

        ExecutorService executorService = new ThreadPoolExecutor(
                4,
                8,
                2,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());



    }
}
