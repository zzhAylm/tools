package com.zzh.juc2;

import java.util.concurrent.*;

/**
 * @Description: 多线程 异步 任务
 * @Author: zzh
 * @Crete 2022/12/24 11:07
 */
public class Juc1 {


    public static void main(String[] args) {

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
            System.out.println("main thread is begin！");
            FutureTask<String> task = new FutureTask<>(() -> {
                System.out.println("futureTask is begin!!");
                TimeUnit.SECONDS.sleep(10);
                System.out.println("futureTask is over!!");
                return "zzh";
            });
            executorService.submit(task);

            //get()的时候，如果子线程刚好执行完成了，就可直接返回结果，如果还没有，就会阻塞执行，
            // 直到结果完成后才会继续往下执行
//            System.out.println("futureTask result="+task.get());
            //等待固定的时间，如果查过这个时间这就直接抛出异常
            System.out.printf("result=%s", task.get(3, TimeUnit.SECONDS));

            System.out.println("main thread is over！");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            executorService.shutdown();

        }
    }
}
