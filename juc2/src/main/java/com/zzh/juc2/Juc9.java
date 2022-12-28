package com.zzh.juc2;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/26 16:03
 */
public class Juc9 {
    static ExecutorService executorService = new ThreadPoolExecutor(
            2,
            4,
            10L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100));
    /**
     * thenRun
     * */
    public static void main(String[] args) {

        //thenAccept:消费型
        CompletableFuture.supplyAsync(()-> "zzh",executorService).thenApply(f-> f).thenAccept(f-> System.out.println("result="+f)).join();

        // thenRun:新开一个线程, 执行任务A ,然后执行任务B，两者没有任何关系，没有相互依赖
        CompletableFuture.supplyAsync(()-> "zzh",executorService).thenRun(()-> System.out.println("新的线程")).join();

        executorService.shutdown();
        System.out.println("main thread is over！");
    }
}
