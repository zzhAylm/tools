package com.zzh.jvm;

/**
 * @Description: 新生代和老年代
 * @Author: zzh
 * @Crete 2023/1/30 19:49
 */
public class Jvm15 {

    /**
     * 年轻代和老年代：
     * 默认新生代占对的三分之一
     * 新生代占三分一，老年代占三分之二
     * 新生代分为 伊甸园区和from区，to区
     * -XX:NewRatio=2 表示新生代占一份，老年代占二分，新生代是堆的三分之一
     * -XX:NewRatio=4 表示新生代占一份，老年代占四分，新生代是堆的五分之一
     * 伊甸园区：幸存者0区：幸存者1区  8：1：1 ， 实际上是6：1：1，资料中写的都是默认8：1：1
     * 需要显示指定添加参数-XX:SurvivorRatio=8 设置伊甸园区和survivor区的比例8：1，(SurvivorRatio默认值8)
     * -XX:-UseAdaptiveSizePolicy,关闭自适应内存分配策略
     *
     * jps ，jstat -gc pid :查看JVm内存分配情况  ， jinfo -flag newRatio pid:查看Jvm的设置的newRatio的参数大小
     *
     * -Xmn2G 设置新生代的大小（一般不设置）
     *
     *
     * 伊甸园区：
     * 新创建的对象都放在伊甸园区
     * 当伊甸园区的内存满的时候就会进行gc，通过gc算法，将可以回收的对象销毁，不能回收的对象放入到Survivor0区
     * 新对象继续创建在伊甸园区，当伊甸园区满的时候（包括Survivor0区的对象），继续进行gc，将可以回收的对象回收，不能回收的对象（包括Survivor0区中存活的对象）放到 Survivor1区
     *
     * 新对象继续创建 循环上面的过程
     *
     * 直到有的对象满15gc次之后，放入到老年代
     *
     * 当伊甸园区满的时候触发 YGC/Minor GC  , 老年代发生垃圾回收的时候叫做 fullGC,major GC
     *
     * 会存在特殊的情况，对象的年龄未满15岁，直接晋升到老年代的情况
     *
     * 80%的对象都是朝生夕死，少部分对象存活
     *
     * 垃圾收集的过程中的特殊情况：
     * 当新对象在伊甸园区无法存储，就会进行YGC,gc之后判断是否可以装下对象，
     * 如果还是无法存储，就会直接在老年代存储（首先判断是否可以存储，如果可以直接存储，不可以进行fullGC，此时如果还是无法存储OOM）
     *
     * 伊甸园区存活的对象，应该放入到Survivor to  区，但是如果Survivor to区放不下对象，就会直接放入到老年代
     * 当老年代无法装下对象的时候，会进行一个FUllGC，gc完成后仍然无法装下对象，则直接OOM
     */
    public static void main(String[] args) {

    }
}
