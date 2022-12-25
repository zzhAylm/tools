package com.zzh.juc2;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/25 23:04
 */
public class Juc4 {
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
        try {
            CompletableFuture.supplyAsync(()->{
                System.out.println("异步任务1，开始");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("异步任务2，结束");
                return "zzh";
            },executorService).whenComplete(((u, throwable) -> {
                if (throwable==null){
                    System.out.println("异步任务2，返回结果="+u);
                }
            })).exceptionally(throwable -> {
                throwable.printStackTrace();
                System.out.println("发生了异常"+throwable);
                return ""+throwable;
            });
            System.out.println("main thread over!!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }


    }
}
