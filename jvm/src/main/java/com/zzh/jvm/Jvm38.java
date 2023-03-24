package com.zzh.jvm;

/**
 * @Description: JVM运行时参数
 * @Author: zzh
 * @Crete 2023/3/23 21:39
 */
public class Jvm38 {

    /**
     * java ： 标准指令 -version
     * java -X :非标准指令  -Xms -Xmx -Xss -Xint -Xmixed
     *
     * java -XX :非标准指令
     * -XX:+UseParallelGC
     * -XX:+UseG1GC
     * -XX:+UseAdaptiveSizePolicy 自动选择年轻代和Survivor区的比例
     *
     * -XX:HeapDumpPath=/Users/zzh
     *
     * jar -Xms50M -Xmx50M -Xss1M -XX:+PrintGCDetails -XX:+PrintGCTimestamps -jar  xxx.jar
     *
     * 运行过程中，修改参数：（有一些参数是无法修改的）
     *
     * jinfo -flag InitialHeapSize=100000 PID  -----非boolean型的参数
     * jinfo -flag +UseG1GC PID   ------ boolean类型的参数
     *
     * 查看可以修改的参数：
     * java -XX:+PrintFlagsFinal -version|grep manageable
     *
     *
     * 打印系统所有的参数的参数： -XX:+PrintFlagsFinal
     *
     * 堆栈参数：
     *          栈：
     *              -Xss128K   -XX:ThreadStackSize=128K 设置鲜橙栈的大小
     *
     *          堆：
     *              -Xms3G    堆最小大小
     *              -Xmx3G   堆最大大小
     *              -Xmn2G   设置新生代大小，推荐设置为整个堆的3/8
     *              -XX:NewSize=1024M      设置新生代初始化大小
     *              -XX:MaxNewSize=1024M    设置新生代最大大小
     *              -XX:SurvivorRatio=8    设置 Eden：Survivor=8:1 ---实际比例是6：1，除非我们显示设置 8：1
     *              -XX:NewRatio=2  设置老年代和年轻代比例 2：1
     *              -XX:+UseAdaptiveSizePolicy   自动选择年轻代和Survivor区的比例
     *              -XX:PretenureSizeThreadshold=1024  设置让大于此阀值的对象直接进入老年代，单位字节
     *              -XX:MaxTenuringThreshold=15  新生代对象的最大的年龄
     *              -XX:+PrintTenuringDistribution : JVm每次minorGC后答应你当前泗洪的Survivor中对象的年龄分布
     *              -XX:TargetSurvivorRatio: MinorGC后Survivor区域中中用空间的期望比例
     *
     *          方法区：
     *              -XX:PermSize=256m  设置永久代大小
     *              -XX:MaxPermSize=256  设置永久代最大大小
     *
     *              -XX:MetaspaceSize   设置元空间大小
     *              -XX:MaxMetaspaceSize  设置元空间最大大小，默认是是没有限制
     *              -XX:+UseCompressedOops  压缩对象指针
     *              -XX:+UserCompressedClassPointers  压缩类指针
     *              -XX:CompressedClassSpaceSize 设置Klass Metaspace 大小，默认1G
     *         直接内存：
     *              -XX:MaxDirectMemorySize 指定DirectMemory容量，未指定，则默认的与java堆最大职一样
     *
     *         OutOfMemory相关参数：
     *              -XX:+HeapDumpOnOutOfMemoryError     表示内存出现OOM时，把Heap转存到Dump文件
     *              -XX:+HeapDumpBeforeFullGC      表示FullGC前，转储Heap，为Dump文件
     *              -XX:HeapDumpPath=/Users/zzh
     *              -XX:OnOutOfMemoryError : 执行一个可行性或者脚本的文件路径，当发生OOM时，去执行这个脚本
     *
     *
     *
     * */
    /**
     *
     * #!bin/bash
     * pid=$(ps -ef|grep Server.jav|awk '{if($8=="java" {print $2})}')
     * kill -9 $pid
     * cd /home/app/sxpservice/bin;
     * sh run.sh
     * */
    public static void main(String[] args) {


    }
}
