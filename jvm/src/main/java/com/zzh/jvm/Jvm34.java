package com.zzh.jvm;

/**
 * @Description: Shenandoah GC 垃圾收集器 和 ZGC 垃圾收集器
 * @Author: zzh
 * @Crete 2023/2/27 08:26
 */
public class Jvm34 {


    /**
     * jdk11 出现的 shenandoah gc 和zgc
     * <p>
     * jdk 8 默认的垃圾收集器器 parallel 收集器 ，jdk5开始使用
     * <p>
     * jdk9 默认 g1 垃圾收集器 ， g1 是在jdk7 开始正式启动
     *
     * shenandoah gc: 是由read heat 研发，jdk12引入的 open jdk 中有，但是oracle gc 没有的垃圾收集器
     *  低延迟做的很好，但是其吞吐量大幅度下降
     */

    /**
     * ZGC : 以低延迟为首要目标，
     * 服务端，大内存，低延迟
     *
     */
    /**
     * 其他垃圾收集器：
     * AliGC , 基于G1算法改造的
     *
     * */
    /**
     * 内存与垃圾回收篇：
     *
     * */
    public static void main(String[] args) {

    }
}
