package com.zzh.juc2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.*;

/**
 * @Description: CompletableFuture的使用
 * @Author: zzh
 * @Crete 2022/12/26 09:47
 */
public class Juc5 {


    //Runnable接口，无参数，无返回值
    //Function: 有参数，有返回值
    //Consumer: 有参数，无返回值
    //BinConsumer：有两个参数，无返回值
    //Supplier: 无参数，有返回值
    public static void main(String[] args) {

        //手动创建线程池
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                4,
                10L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100));

        try {
            CompletableFuture<String> exceptionally = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "当前线程");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return "zzh";
            }, executorService).whenComplete((s, throwable) -> {
                if (throwable == null) {
                    System.out.println("result=" + s);
                }
            }).exceptionally(throwable -> {
                System.out.println("异常了，e=" + throwable);
                return "fail";
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            executorService.shutdown();
        }


    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Student {
        private String name;
        private Integer id;
        private String major;
    }

}
