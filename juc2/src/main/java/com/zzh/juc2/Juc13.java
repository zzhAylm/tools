package com.zzh.juc2;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/26 17:35
 */
public class Juc13 {

    static ExecutorService executorService = new ThreadPoolExecutor(
            2,
            4,
            10L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100));

    public static void main(String[] args) {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                    System.out.println("任务一执行");
                    return 10;
                }, executorService)
                .thenCombine(CompletableFuture.supplyAsync(() -> {
                    System.out.println("任务2执行");
                    return 20;
                }, executorService), (x, y) -> {
                    System.out.println("任务1，任务2合并");
                    return x + y;
                }).thenCombine(CompletableFuture.supplyAsync(() -> {
                    System.out.println("任务3合并");
                    return 30;
                }, executorService), (x, y) -> {
                    System.out.println("任务1，任务2，任务3合并");
                    return x + y;
                });
        System.out.println("result="+future.join());
        executorService.shutdown();
    }
}
