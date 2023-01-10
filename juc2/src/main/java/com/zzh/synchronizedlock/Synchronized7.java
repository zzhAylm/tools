package com.zzh.synchronizedlock;

/**
 * @Description: 轻量级锁 本质就是自旋锁
 * @Author: zzh
 * @Crete 2023/1/9 22:26
 */
public class Synchronized7 {


    // 自旋锁，自选10次都不成功，会升级为重量级锁

    //  jdk6之后： 使用了自适用自旋锁，轻量级锁和偏向锁锁的不同：
    // 自旋锁抢占失败时候会自旋， 偏向锁抢占失败后会升级位轻量级锁
    // 轻量级锁每次退出同步代码快都释放锁， 而偏向锁只有在竞争发生的才会释放锁
    // 轻量级锁比偏向锁的成本更高一些
    public static void main(String[] args) {





    }
}
