package com.zzh.juc2;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/25 21:18
 */
public class Juc3 {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService =
                new ThreadPoolExecutor(
                        2,
                        4,
                        2L,
                        TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(100),
                        Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.AbortPolicy());


        //又返回值的异步任务
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "zzh";
        },executorService);

        //使用CompletableFuture,代替Future:
        stringCompletableFuture.whenComplete(((s, throwable) -> {
            System.out.println("result="+s);
            System.out.println("throwable="+throwable);
        })).get();


        //无返回值的异步任务
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("异步任务开始");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("异步任务结束");
        }, executorService);

        future.whenComplete((unused, throwable) -> {
            System.out.println("unused="+unused);
            System.out.println("throwable="+throwable);
        }).get();

    }
}
