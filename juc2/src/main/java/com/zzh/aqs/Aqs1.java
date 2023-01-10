package com.zzh.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/10 19:39
 */
public class Aqs1 {

    //AQS: 抽象的队列同步工具 ，是抽象队列同步器，AQS定义了一套多线程访问共享资源的同步器框架。
    // 有一个双队列
    // 头指针 尾指针 状态位， 队列

    // AQS： 有阻塞需要排队，实现排队必然需要队列 ， state状态位变量+CLH双端队列
    //
    public static void main(String[] args) {

//        AbstractQueuedSynchronizer
    }
}
