package com.zzh.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 线程池
 * @Author: zzh
 * @Crete 2022/12/22 23:01
 */
public class ThreadPool1 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        ExecutorService executorService2 = Executors.newCachedThreadPool();
    }
}
