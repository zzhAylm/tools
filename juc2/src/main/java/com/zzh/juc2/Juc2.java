package com.zzh.juc2;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/24 11:28
 */
public class Juc2 {
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
            while (true) {
                if (task.isDone()) {
                    System.out.printf("future task result=%s\n", task.get(3, TimeUnit.SECONDS));
                    break;
                } else {
                    System.out.println("future task is not over！");
                    TimeUnit.SECONDS.sleep(1);
                }
            }
            System.out.println("main thread is over！");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
    }
}
