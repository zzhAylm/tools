package com.zzh.jvm;

/**
 * @Description: 内存分配策略
 * @Author: zzh
 * @Crete 2023/1/31 20:52
 */
public class Jvm17 {

    //  -Xms60m -Xms60m -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails

    // 程序中我们不要创建过多的大对象，可能会直接分配到老年代中
    // TLAB 在eden区中的一个区域，是线程私有的，可以通过 -XX:UseTLAB 参数设置是否使用 ，仅占eden区的 1%
    // 对中共享的数据是数据不安全的，为了实现数据安全，我们可以使用TLAB，来实现数据安全（这种方式比加锁更加快）

    // 线程安全的机制，如果new的对象可以分配到tlab区域，就可以不加锁，直接分配到堆的区域，只有tlab区域无法容纳此对象，jvm才会尝试使用锁机制
    //
    public static void main(String[] args) {


    }
}
