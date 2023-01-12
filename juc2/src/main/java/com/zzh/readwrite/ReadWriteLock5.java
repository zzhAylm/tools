package com.zzh.readwrite;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/11 22:26
 */
public class ReadWriteLock5 {

    //ReadWriteLock: StampedLock:，使用的是乐观锁，支持读的时候，允许写
    // 如果期间发生了改变，使用乐观锁，重新读
    //


    // 写后读，使用锁降级策略
    //
    public static void main(String[] args) {

    }
}
