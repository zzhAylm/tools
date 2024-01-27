package com.zzh.boot3.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/24 17:31
 */
@Slf4j
public class FutureTaskTest {


    @Test
    public void futureTaskTest() throws ExecutionException, InterruptedException {

        FutureTask<String> futureTask = new FutureTask<>(() -> {
            Thread.sleep(1000 * 5);
            return "执行完成";
        });
        futureTask.run();

        log.info("其他任务执行");
        log.info("其他任务执行");
        log.info("其他任务执行");
        log.info("其他任务执行");

        System.out.println(futureTask.get());
    }

    @Test
    public void completableFutureTest(){
        // 异步执行任务，并返回计算结果
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            // 模拟耗时操作
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        try {
            Integer result = future.get();
            System.out.println("计算结果为：" + result);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("等待任务执行出错：" + e.getMessage());
        }
    }
}
