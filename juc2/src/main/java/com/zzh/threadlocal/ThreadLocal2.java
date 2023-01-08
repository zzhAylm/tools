package com.zzh.threadlocal;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/7 20:43
 */
public class ThreadLocal2 {

    public static void main(String[] args) {

        MyData myData=new MyData();

        ExecutorService executorService= Executors.newFixedThreadPool(3);

        try {
            for (int i = 0; i < 10; i++) {

                executorService.submit(()->{
                    try {
                        Integer before = myData.threadLocal.get();
                        myData.add();
                        Integer after = myData.threadLocal.get();
                        System.out.println(Thread.currentThread().getName()+"\t before:"+before+"\t after:"+after);
                    } finally {
                        //每个线程执行结束的时候要删除数据，方式线程复用的时候，造成其他的线程数据错误
                        myData.threadLocal.remove();
                    }
                });

            }
        } finally {
            executorService.shutdown();
        }


    }


    static class MyData{
        ThreadLocal<Integer> threadLocal=ThreadLocal.withInitial(()->0);

        public void add(){
            threadLocal.set(threadLocal.get()+1);
        }
    }
}
