package com.zzh.juc2;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/26 17:28
 */
public class Juc12 {

    static ExecutorService executorService = new ThreadPoolExecutor(
            2,
            4,
            10L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100));


    public static void main(String[] args) {

        //thenCombine : 合并两个future 的结果
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 10;
        }, executorService);

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 20;
        }, executorService);

        //合并两个任务的结果
        CompletableFuture<Integer> future = future1.thenCombine(future2, (f1, f2) -> {
            System.out.println("result=" + (f1 + f2));
            return f1 + f2;
        });
        System.out.printf("最终结果=%s\n", future.join());
        executorService.shutdown();
    }
}
