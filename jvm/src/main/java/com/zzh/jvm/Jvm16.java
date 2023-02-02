package com.zzh.jvm;

/**
 * @Description: YGC/minor GC  major GC/full GC
 * @Author: zzh
 * @Crete 2023/1/31 09:56
 */
public class Jvm16 {

    // minor major ,full gc
    // full GC 包括堆空间和方法区
    // minor GC / Young GC:新生代
    // major gc / old gC：老年代
    // minor GC : 当年轻代的eden区满的时候会触发GC
    // 不要即将full GC和major GC混淆
    //
    public static void main(String[] args) {



    }
}
