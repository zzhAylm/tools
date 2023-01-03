package com.zzh.volatiles;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/3 21:57
 */
public class Volatile5 {

    //volatile : volatile可见性：对于volatile修饰的变量，每次修改都会立刻刷新到主内存中，其他线程会立刻察觉到值被修改了
    // 每次读，读到的数据都是主内存中最新的值
    // 没有原子性： 不能保存某个操作是原子操作，线程安全的
    // 禁止指令重排： 可以禁止编译指令的重新排序，导致的最终决过不同
    public static void main(String[] args) {

    }
}
