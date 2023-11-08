package com.zzh.juc.thread;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description: 集合的线程安全问题
 * @Author: zzh
 * @Crete 2022/12/21 00:33
 */
public class Thread4 {

    public static void main(String[] args) {
        //list： 线程不安全，会出现异常
//        List<String> list=new ArrayList<>();
        // vector: 可以解决但是不常用
//        List<String> list=new Vector<>();

        // 使用 Collections 工具类
//        List<String> list = Collections.synchronizedList(new ArrayList<>());

        //copyOnWriteArrayList : 写时复制技术，读可以并发的读取，
        // 写的时候只能有一个人写，并且复制一份和以前一样的数据
        // ，然后更改新的内容，修改完成后，将指针指向新的内存（覆盖之前的集合数据）
        //并发的读，独立写
        List<String> list=new CopyOnWriteArrayList<>();
        String s = list.get(0);
        for (int i = 0; i < 30; i++) {
            new Thread(() ->{
                list.add(UUID.randomUUID().toString().substring(8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }

    }
}
