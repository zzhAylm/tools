package com.zzh.jvm;

/**
 * @Description: 堆
 * @Author: zzh
 * @Crete 2023/1/30 15:41
 */
public class Jvm14 {


    // 设置堆的大小与OOM，年轻代，老年代，
    // Jvm常用的参数：
    /**
     * 一个Jvm实例只存在一个堆内存，Jvm启动的时候堆就会创建，空间也就确定了，这是Jvm管理的最大的一块内存空间
     *  -Xmx -Xms -Xmn 调整堆内存的大小
     *  -XX:+PrintGcDetails :打印gc的细节信息
     *  堆是gc的主要区域
     *
     *  现代垃圾收集器大部分都是居于分代收集理论设计的
     *
     *  Java7及之前逻辑上分为：新生区+养老区+永久代
     *
     *  Java8 逻辑上分为：新生代(PSYoungGen)+养老代(parOldGen)+元空间（Metaspace）
     *
     *  新生区：伊甸园区(eden)，幸存者0区(from)，幸存者1区(to)
     *
     *  物理上：永久代和元空间->方法区
     *  Jdk8的内存变化：永久代变成元空间
     *
     *  -Xmx和-Xms 开发中建议将初始内存和最大内存设置成一样的大小
     *
     *  查看内存情况： 方式一：jps 和 jstat -gc pid 查看内存大小
     *               方式二： -XX:+PrintGCDetails
     *
     *  Throwable : Exception和Error
     *
     *
     *
     * */
    public static void main(String[] args) {

        System.out.println("-Xms 调整堆的最小内存大小");
        System.out.println("-Xmx 调整堆的最大内存大小");
        System.out.println("-Xmn 调整新生代的大小");
        System.out.println("-Xss 调整栈的大小");

        long initMemory = Runtime.getRuntime().totalMemory()/1024/1024;

        long maxMemory = Runtime.getRuntime().maxMemory()/1024/1024;

        System.out.println("initMemory="+initMemory);
        System.out.println("maxMemory="+maxMemory);



    }
}
