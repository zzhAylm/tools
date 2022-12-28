package com.zzh.juc2;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/26 16:44
 */
public class Juc11 {

    static ExecutorService executorService = new ThreadPoolExecutor(
            2,
            4,
            10L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100));

    public static void main(String[] args) {

        //applyToEither 比较两个任务的速度，返回速度比较快的任务
        CompletableFuture<String> taskA = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "taskA";
        }, executorService);
        CompletableFuture<String> taskB = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "taskB";
        }, executorService);

        CompletableFuture<String> result = taskA.applyToEither(taskB, f -> {
            return f + " is fast more";
        });
        executorService.shutdown();
        System.out.println("result="+result.join());
    }
}
