package com.zzh.boot3.future;

import com.google.common.util.concurrent.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/4/22 15:05
 */
public class FutureTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        ListeningExecutorService guavaExecutor = MoreExecutors.listeningDecorator(executor);
        ListenableFuture<String> future1 = guavaExecutor.submit(() -> {
            //step 1
            System.out.println("执行step 1");
            return "step1 result";
        });
        ListenableFuture<String> future2 = guavaExecutor.submit(() -> {
            //step 2
            System.out.println("执行step 2");
            return "step2 result";
        });
        ListenableFuture<List<String>> future1And2 = Futures.allAsList(future1, future2);
        Futures.addCallback(future1And2, new FutureCallback<List<String>>() {
            @Override
            public void onSuccess(List<String> result) {
                System.out.println(result);
                ListenableFuture<String> future3 = guavaExecutor.submit(() -> {
                    System.out.println("执行step 3");
                    return "step3 result";
                });
                Futures.addCallback(future3, new FutureCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println(result);
                    }
                    @Override
                    public void onFailure(Throwable t) {
                    }
                }, guavaExecutor);
            }
            @Override
            public void onFailure(Throwable t) {
            }}, guavaExecutor);
    }

    @Test
    public void testCompletableFutureTest(){
        ExecutorService executor = Executors.newFixedThreadPool(5);

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行step 1");
            return "step1 result";
        }, executor);
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行step 2");
            return "step2 result";
        });
        cf1.thenCombine(cf2, (result1, result2) -> {
            System.out.println(result1 + " , " + result2);
            System.out.println("执行step 3");
            return "step3 result";
        }).thenAccept(result3 -> System.out.println(result3));

    }

    @Test
    public void testCompletableFuturesTest2(){
        ExecutorService executor = Executors.newFixedThreadPool(5);
//1、使用runAsync或supplyAsync发起异步调用
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            return "result1";
        }, executor);
//2、CompletableFuture.completedFuture()直接创建一个已完成状态的CompletableFuture
        CompletableFuture<String> cf2 = CompletableFuture.completedFuture("result2");
//3、先初始化一个未完成的CompletableFuture，然后通过complete()、completeExceptionally()，完成该CompletableFuture
        CompletableFuture<String> cf = new CompletableFuture<>();

        cf.complete("success");
        cf2.join();

    }




    @Test
    public void testCompletableFutureTest3(){
        ExecutorService executor = Executors.newFixedThreadPool(5);
//1、使用runAsync或supplyAsync发起异步调用
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            return "result1";
        }, executor);
//2、CompletableFuture.completedFuture()直接创建一个已完成状态的CompletableFuture
        CompletableFuture<String> cf2 = CompletableFuture.completedFuture("result2");
    }
//
//    @FunctionalInterface
//    public interface ThriftAsyncCall {
//        void invoke() throws TException;
//    }
//    /**
//     * 该方法为美团内部rpc注册监听的封装，可以作为其他实现的参照
//     * OctoThriftCallback 为thrift回调方法
//     * ThriftAsyncCall 为自定义函数，用来表示一次thrift调用（定义如上）
//     */
//    public static <T> CompletableFuture<T> toCompletableFuture(final OctoThriftCallback<?,T> callback , ThriftAsyncCall thriftCall) {
//        //新建一个未完成的CompletableFuture
//        CompletableFuture<T> resultFuture = new CompletableFuture<>();
//        //监听回调的完成，并且与CompletableFuture同步状态
//        callback.addObserver(new OctoObserver<T>() {
//            @Override
//            public void onSuccess(T t) {
//                resultFuture.complete(t);
//            }
//            @Override
//            public void onFailure(Throwable throwable) {
//                resultFuture.completeExceptionally(throwable);
//            }
//        });
//        if (thriftCall != null) {
//            try {
//                thriftCall.invoke();
//            } catch (TException e) {
//                resultFuture.completeExceptionally(e);
//            }
//        }
//        return resultFuture;
//    }


    public void completableFutureTest3(){
        ExecutorService executor = Executors.newFixedThreadPool(5);
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            return "result1";
        }, executor);

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "result2", executor);

    }




}
