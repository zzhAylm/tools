package com.zzh.jvm;

/**
 * @Description: 堆空间参数设置总结
 * @Author: zzh
 * @Crete 2023/2/1 18:26
 */
public class Jvm18 {

    //  -XX:+PrintFlagsInitial: 查看参数的默认值
    // -XX:+PrintFlagsFinal ： 查看所有的参数的最终值（可能存在修改，不再是初始值）
    // -Xms: 堆的初始空间大小 (默认是虚拟机的 1/64)
    // -Xmx: 堆的最大空间 （默认是虚拟机的 1/4）
    // -Xmn: 新生代空间的大小，一般是整个堆的三分之一
    // -Xss: 栈的大小
    //  -XX:NewRatio:设置新生代与老年代的比例，默认值2，新生代：老年代 = 1：2
    // -XX:SurvivorRatio :设置新生代中，默认值8， eden区：Survivor区= 8：1
    // -XX:+PrintGCDetails : 打印gc日志的细节信息  ， -XX:+PrintGC 加  -verbose:gc
    // -XX:MaxTenuringThreshold : 设置新生代对象的最大年龄
    // -XX:HandlePromotionFailure: 是否设置空间分配担保，可以不到对象的最大年龄，就直接进入到老年代
    // STW：用户线程暂停时间
    //
    public static void main(String[] args) {

        System.out.println("-XX:+PrintFlagsInitial");
    }
}
