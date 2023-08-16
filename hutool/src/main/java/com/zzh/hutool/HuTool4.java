package com.zzh.hutool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/8/7 20:39
 */
public class HuTool4 {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        threadLocal.set("zzh");
        threadLocal.get();

        Thread thread = new Thread();
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("zzh-thread" + "-%d")
                .setDaemon(true).build();
        ExecutorService threadPool = new ThreadPoolExecutor(4, 8, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1024), threadFactory);

        FutureTask<String> futureTask=new FutureTask<>(()-> null);
        futureTask.get();
    }
}
