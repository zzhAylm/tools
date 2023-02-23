package com.zzh.jvm;

/**
 * @Description: 垃圾收集器的介绍
 * @Author: zzh
 * @Crete 2023/2/19 13:03
 */
public class Jvm32 {

    // -XX:+PrintCommandLineFlags : 打印gc收集器参数
    // -XX:UseSerialGC : 表明新生代使用Serial GC 同时老年代使用Serial oldGC
    // Serial 和 Serial old  ：低消耗
    // parNew  和CMS ： 在jdk9中被标记为过期
    // Parallel 和 Parallel old  ： jdk8 默认的垃圾收集器  ： 高吞吐量
    // cms : 老年代垃圾回收器，低延迟，当内存达到一定量的时候就会进行垃圾回收 ，第一款并发垃圾收集器，在jdk14中被去除掉，标记清除，会产生垃圾碎片，
    // 垃圾碎片最终会导致full gc ，使用 serial old 单线程垃圾回收，stop the world 时间较长, jdk 14 移除了cms
    // G1 ： jdk9 默认的垃圾收集器
    /**
     * G1垃圾回收器：区域化分代式 ，jdk7 引入的垃圾回收器，适合堆更大的情况，
     * 并发与并行，
     * 逻辑分代（兼顾老年代和新生代的回收）
     * ，空间整合，使用复制算法不会产生垃圾碎片 ，
     * 可预测的时间模型（每次回收一部分region可以不会产生停顿，维护一个优先级列表，会尽量在可控时间内，回收价值最大的region,获得更高的回收效率）
     * 缺点：占用较多的内存，应用内存小的时候cms的性能优与G1
     * G1的参数设置：
     *  -XX:+UseG1GC 使用G1收集器
     *  -XX:+Xmx6G 堆最大内存
     *  -XX:+Xms6G 堆最小内存
     *  -XX:MaxGCPauseMillis200ms 期望的最大停顿时间
     *
     *  G1使用场景：当堆内存较多，切追求低延迟的时候使用g1垃圾收集器，G1每次收集的只是一部分region，保证每次清理的停顿时间都不会过长
     *  当超过50%的Java堆被活动数据占用，对象的分配频率或者年代的提升频率变化很大，gc停顿时间长 ，使用G1代替cms会更好
     *
     * TLAB:在堆中那个线程私有的一块区域
     * Bump-the-pointer :指针碰撞
     *
     * G1垃圾收集器的回收过程：
     *
     *  新生代  记忆级：垃圾收集的时候通过 gcroot 不可达的对象进行回收，g1垃圾收集器分为多个region ， 如果回收一个region ，可能需要别的region的gcroot是否有应用这个region的引用
     *  ，但是如果我们需要全局扫描的的话，消耗太大，为了解决这个问题，我们引入了remembered set，每个一个region都有一个remembered set都有一个这样的集合，记录着其他regin引用本region中的对象
     *  这样每次回收的时候就可以通过remembered set 直接回收不再remembered set 中的对象，增加回收效率
     *
     *  年轻gc -> 年轻gc+并发标记  -> 混合回收   （可选 full gc）
     *
     *
     * ZGC垃圾收集器：
     *
     * */
    public static void main(String[] args) {






    }
}
