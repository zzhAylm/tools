package com.zzh.synchronizedlock;

/**
 * @Description: 重量级锁
 * @Author: zzh
 * @Crete 2023/1/10 10:08
 */
public class Synchronized8 {

    // synchronized : 重量级锁是获取对象的monitor对象，获取到锁之后，ObjectMonitor中的Woner指向当前线程，Monitorentry进入同步代码快
    // 执行完之后Monitroexsit ,退出同步代码块，释放锁

    // 无锁----------偏向锁----------（有线程竞争，尝试cas替换对象头中标志位中的线程ID，并且cas替换失败，且获持有锁的线程还在执行，升级）-------
    // 轻量级锁--------（当前线程持有当前锁，其他线程cas自选等待，当自选数量超过10次还没有成功，升级）--------重量级锁


    // 偏向锁，每次线执行结束后，锁不会释放，偏向线程Id存在，开销小，性能高
    // 轻量级锁：获取线程到需要加锁，和解锁，每次获取线程失败后，会进行自选等待，不会直接阻塞，挂起线程
    // 重量级锁： 每次获取线程，都需要加锁和解锁，并且获取失败的线程，需要进去阻塞队列，挂起线程，执行的时候还需要再次被唤醒
    // 注： 加锁解锁操作需要操作系统配合，用户态到内核台的切换，非常耗时  ， 线程的状态切换也非常耗时
    public static void main(String[] args) {

    }
}
