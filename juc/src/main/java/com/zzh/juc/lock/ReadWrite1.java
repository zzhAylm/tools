package com.zzh.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/22 18:35
 */
public class ReadWrite1 {

    private volatile Map<String, String> map = new HashMap<>();

    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key,String value) throws InterruptedException {
        rwLock.writeLock().lock();
        try {

            map.put(key,value);
            TimeUnit.SECONDS.sleep(2);
            System.out.println("添加数据key="+key);


        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void get(String key) throws InterruptedException {
        rwLock.readLock().lock();
        try {
            map.get(key);
            TimeUnit.SECONDS.sleep(2);
            System.out.println("获取值key="+key);

        } finally {
            rwLock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        ReadWrite1 readWrite1 = new ReadWrite1();

        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                try {
                    readWrite1.get(Thread.currentThread().getName().replace("read", "write"));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            },"read"+i).start();

        }

        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                try {
                    readWrite1.put(Thread.currentThread().getName(), Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            },"write"+i).start();

        }


    }

}
