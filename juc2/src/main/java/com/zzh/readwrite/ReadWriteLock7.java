package com.zzh.readwrite;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/12 14:19
 */
public class ReadWriteLock7 {


    //每次加锁的时候，都会返回一个邮戳，解锁的时候需要 添加一个邮戳标记
    static StampedLock stampedLock = new StampedLock();

    public static void main(String[] args) throws InterruptedException {

//        // 乐观读：乐观读的时候，可以有写线程进入
//        long stamp = stampedLock.tryOptimisticRead();
//        //通过邮戳标记，检测 读锁返回的数据是否有人修改，true：表示读期间没有修改，false：表示期间有人修改，需要重新悲观读
//        boolean validate = stampedLock.validate(stamp);
//        //悲观读： 悲观读的时候，写线程不可以介入
//        long stamp1 = stampedLock.readLock();

        //乐观读：加锁之后不需要解锁

        // StampedLock: 有点效率更高，读锁进行的时候，写锁也可以进入，防止写锁的饥饿问题
        // StampedLock 缺点： 不支持重入，没有Condition不支持线程通信，不能调用interrupt()
        new Thread(ReadWriteLock7::read, "read").start();
        TimeUnit.SECONDS.sleep(3);
        new Thread(ReadWriteLock7::write, "write").start();

    }
    public static void read() {
        long stamp = stampedLock.tryOptimisticRead();
        try {
            System.out.println(Thread.currentThread().getName() + "读线程进入," + stampedLock.validate(stamp));
            for (int i = 0; i < 5; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + "正在读取，是否已经被其他写线程修改了" + stampedLock.validate(stamp));
            }
            //有其他线程修改了数据，此次的数据无法时候，需要使用被关锁，重新读取
            if (!stampedLock.validate(stamp)) {
                long readStamp = stampedLock.readLock();
                try {
                    System.out.println("悲观读进入");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("悲观读结束");
                } finally {
                    stampedLock.unlockRead(readStamp);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void write() {
        long stamp = stampedLock.writeLock();
        try {
            System.out.println(Thread.currentThread().getName() + "写线程进入");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(Thread.currentThread().getName() + "写线程结束");
            stampedLock.unlockWrite(stamp);
        }
    }
}
