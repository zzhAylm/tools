package com.zzh.juc.compalefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Description: 异步回调
 * @Author: zzh
 * @Crete 2022/12/22 23:57
 */
public class CompletableFuture1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //异步回调
        CompletableFuture<Void> future=CompletableFuture.runAsync(()->{
            System.out.println("异步调用无返回值");
        });

        //.get()方法，执行异步任务，并返回结果
        future.get();

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步调用you返回值");
            return "1024";
        });

        stringCompletableFuture.whenComplete((t,u)->{
            System.out.println("t+"+t);
            System.out.println("u+"+u);
        }).get();
    }
}
