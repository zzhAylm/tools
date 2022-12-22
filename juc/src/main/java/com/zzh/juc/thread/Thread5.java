package com.zzh.juc.thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Description: HashMap 和 HashSet 不安全问题
 * @Author: zzh
 * @Crete 2022/12/21 00:57
 */
public class Thread5 {

    public static void main(String[] args) {
        //HashMap:线程不安全问题
        Map<String,String> map=new HashMap<>();
        //解决办法：使用ConcurrentHashMap
        Map<String,String> map1=new ConcurrentHashMap<>();

        //线程不安群
        Set<String> set = new HashSet<>();
        //解决办法，使用CopyOnWriteArraySet，解决线程安全问题
        Set<String> set1 = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 20; i++) {
            new Thread(() ->{
                set.add(UUID.randomUUID().toString().substring(8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
