package com.zzh.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Description: 分支合并框架
 * @Author: zzh
 * @Crete 2022/12/22 23:36
 */
public class ForkJoin1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyTask myTask = new MyTask(1, 100);

        ForkJoinPool forkJoinPool=new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);

        System.out.printf("%s", forkJoinTask.get());

    }

    public static class MyTask extends RecursiveTask<Integer> {

        private Integer begin;
        private Integer end;
        private Integer result=0;

        public MyTask(Integer begin, Integer end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (begin - end < 10) {
                for (int i = begin; i <= end; i++) {
                    result += i;
                }
            } else {

                int min = begin + (end - begin) / 2;

                MyTask left = new MyTask(begin, min);
                MyTask right = new MyTask(min + 1, end);

                left.fork();
                right.fork();

                result = left.join() + right.join();

            }
            return result;
        }
    }
}
