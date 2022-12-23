package com.zzh.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 锁降级 ,先获取写锁，然后在获取读锁，然后释放写锁，最后释放读锁，写锁降级为读锁
 * @Author: zzh
 * @Crete 2022/12/22 18:35
 */
public class ReadWrite2 {
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    public void method() {
        try {
            readWriteLock.writeLock().lock();

            readWriteLock.readLock().lock();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock.writeLock().unlock();
            readWriteLock.readLock().unlock();
        }


    }
}
