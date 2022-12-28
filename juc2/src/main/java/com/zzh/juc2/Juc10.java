package com.zzh.juc2;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/26 16:29
 */
public class Juc10 {
    static ExecutorService executorService = new ThreadPoolExecutor(
            2,
            4,
            10L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100));

    public static void main(String[] args) {
        // 是用 thenRun 和 thenRunAsync 的区别：
        // 如果thenRun 指定了线程池，后面的thenRun 也使用这个线程池
        // 如果thenRunAsync 则 不会只用第一个异步任务指定的线程池，还是会使用默认的 ForkJoinPool
        // 带有异步的步骤都是默认使用 forkJoinPool
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return 1;
                }, executorService)
                .thenRun(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }).thenRun(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }).thenRun(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });


        CompletableFuture<Void> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 1;
        }, executorService).thenRunAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).thenRunAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).thenRunAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        future.join();
        future1.join();

        executorService.shutdown();

        System.out.println("main thread is over!");
    }
}
