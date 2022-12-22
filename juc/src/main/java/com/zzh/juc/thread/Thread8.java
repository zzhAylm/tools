package com.zzh.juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description: 线程的实现方式
 * @Author: zzh
 * @Crete 2022/12/22 00:08
 */
public class Thread8 {

    //runnable 和callable 的不同：
    // runnable 没有返回值，callable又返回值
    //runnable 没有异常返回，callable可能会有异常
    //futureTask ：计算的过程只需要进行一次，可以多次获取不会重复计算
    // 继承Thread
    //实现runnable
    //实现callable
    //线程是的方式
    //futureTask ： 实现callable接口的使用，任务回调，异步任务执行，主线程继续往下执行，futureTask任务使用另外的线程执行
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadRun threadRun = new ThreadRun();
        new Thread(threadRun,"runnable").start();

        ThreadCall call=new ThreadCall();

        FutureTask<String> target = new FutureTask<>(call);
        new Thread(target,"B").start();

        while (target.isDone()){
            System.out.printf("%s\n", target.get());
        }

        FutureTask<String> futureTask = new FutureTask<>(() -> "zzh");

        new Thread(futureTask,"futureTask").start();

        while (futureTask.isDone()){
            System.out.println(futureTask.get());
        }
    }


    public static class ThreadRun implements Runnable{

        @Override
        public void run() {

        }
    }

    public static class ThreadCall implements Callable<String> {


        @Override
        public String call() throws Exception {

            return "zzh";
        }
    }


}
