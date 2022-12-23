package com.zzh.juc.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Description: 阻塞队列
 * @Author: zzh
 * @Crete 2022/12/22 22:34
 */
public class BlockQueue1 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(100);

        //满了之后会抛异常
        blockingQueue.add("a");
        //如果队列为空，会抛异常
        blockingQueue.remove();
        //队列无数据会抛异常
        blockingQueue.element();


        //队列满会返回false
        blockingQueue.offer("a");

        //队列空会返回 null
        blockingQueue.poll();
        //队列为空返回null
        String peek = blockingQueue.peek();

        //队列满会阻塞
        blockingQueue.put("a");
        //队列空会阻塞
        blockingQueue.take();
    }
}
