package com.zzh.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: GC日志分析
 * @Author: zzh
 * @Crete 2023/2/23 20:55
 */
public class Jvm33 {

    /**
     * jdk8 GC参数设置：
     *
     * -XX:+PrintGCDetails : 打印日志回收细节
     * -Xms60M
     * -Xmx60M
     * <p>
     * -Xmn20M ,默认新生代是整个堆的三分之一
     * <p>
     * -XX:SurvivorRatio=8 , 设置 幸存者1区 ：幸存者0区：伊甸园区 = 1：1：8
     *
     * -XX:+UseSerialGC : 使用Serial垃圾收收集器
     *
     * -XX:+PrintGCTimeStamps
     * -XX:+PrintGCDateStamps
     * -XX:+PrintHeapAtGC
     * -Xloggc:../logs/gc.log
     *
     * -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps
     *
     *
     *  (Allocation Failure): 发生GC回收的原因是代表Eden无法分配内存
     *
     *  jdk8：大对象在新生代无法分配足够的内存时，会发生一次gc，如果新生代还是不够的，会将大对象直接分配到老年代
     *  jdk7：大对象在新生代无法分配足够的内存时，会发生一次gc，将现有的新生代对象分配到老年代，然后在吧大对象分配到新生代
     *
     *  GC日志分析工具：
     *  保存gc日志文件：-Xlog:gc:./logs/gc.log
     *  GC日志分析工具：GCeasy在线工具 ，gcViewer工具
     *
     *
     */
    public static void main(String[] args) {

        String str="fadsfadsfadsfdsf你好fasdfadsf你受";

        System.out.println(str.substring(0, 20));

        List<byte[]> bytesList=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            bytesList.add(new byte[1024 * 1024]);
        }

    }
}
