package com.zzh.jvm;

/**
 * @Description: 垃圾回收，和Java中引用的交流
 * @Author: zzh
 * @Crete 2023/2/9 17:33
 */
public class Jvm28 {
    /**
     * 垃圾回收机制：
     * System.gc() 进行full GC
     * <p>
     * 无法保证对垃圾收集器的调用，无法保证什么时候执行，仅仅是提醒垃圾收集器进行垃圾收集
     */
    public static void main(String[] args) {
        // full GC ， 提醒jvm的垃圾回收器执行GC
        new Test();
        System.gc(); //RunTime.getRunningTime().gc
    }


    public static class Test {

        // 对象被回收之前会调用此方法，
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("对象被回收了");
        }
    }
}
