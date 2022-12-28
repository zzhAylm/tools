package com.zzh.juc2;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/26 15:53
 */
public class Juc8 {
    static ExecutorService executorService = new ThreadPoolExecutor(
            2,
            4,
            10L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100));

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 计算结果持续处理,串行好处理方式 thenApply()
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 1;
        }, executorService).thenApply(f -> {
            System.out.println("第二步");
            return ++f;
        }).thenApply(f -> {
            System.out.println("第三步");
            return ++f;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("result=" + v);
            }
        }).exceptionally(throwable -> {
            System.out.println("exception=" + throwable);
            return null;
        });

        //串行好处理方式，handle()
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 1;
        }, executorService).handle((f, e) -> {
            System.out.println("第二步");
            return ++f;
        }).handle((f, e) -> {
            System.out.println("第三步");
            return ++f;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("result=" + v);
            }
        }).exceptionally(throwable -> {
            System.out.println("exception=" + throwable);
            return null;
        });

        executorService.shutdown();

        System.out.println("main thread is over!");
    }
}
