package com.zzh.jvm;

/**
 * @Description: 堆是分配对象存储的唯一选择吗？
 * @Author: zzh
 * @Crete 2023/2/1 20:59
 */
public class Jvm19 {

    // 对象主要分配在堆上，
    // 逃逸分析技术：检测一个对象是否是逃逸出一个方法，没有逃逸出则认为他可以在栈上分配对象，减少堆的压力
    // jdk7之后是默认开启的逃逸分析，-XX:+DoEscapeAnalysis 显示开启逃逸分析技术
    // 通过-XX:+PrintEscapeAnalysis 查看逃逸分析的筛选结果
    // 如果在方法中new出来的对象，是会赋值给外面的的成员变量，或者是方法返回这个对象，或者 此对象是由外面参数参入了，则判定此方法发生了逃逸
    // 开启逃逸分析后，对象的内存分配速度快，不会发生gc，维护的实例数量较少
    //
    // 逃逸分析的技术：
    // 栈上分配

    //同步省略：会将不合理的同步策略，自动的消除

    /**
     * public void method(){
     * Object object=new Object();
     * synchronized(object){
     * <p>
     * }
     * }
     */

    // 标量替换（会把未发生逃逸的聚合量分解成若干个成员变量，叫做标量替换，放入到栈空间，减少对内存分配，-XX:+EliminateAllocations:开启标量替换，默认是开启的，允许对象打散分配到栈上）

    // 堆空间是内存分配的唯一选择吗？ no，我们还可以通过逃逸技术分析，将对象分配到栈上
    // -server -Xmx10M -Xms10M -XX:+DoEscapeAnalysis -XX:+PrintGCDetails -XX:+EliminateAllocations
    // -XX:+EliminateAllocations  : 开启标量替换，默认打开，允许将对象打散分配到栈上，比如对象有 id和name 两个字段，这两个字段将被视为独立的局部变量进行分配
    public static void main(String[] args) {

        String str = new String("zzh");


    }
}
