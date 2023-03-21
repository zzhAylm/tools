package com.zzh.jvm;

import java.util.Scanner;

/**
 * @Description: jvm调优命令总结
 * @Author: zzh
 * @Crete 2023/3/11 18:10
 */
public class Jvm36 {

    /**
     * jps：查看虚拟机进程
     * jps工具的使用非常简单，只需在命令行窗口中输入"jps"命令即可。该命令会列出当前正在运行的所有Java进程的进程ID和主类名称。
     * 可以使用"-l"选项来显示完整的主类名称，使用"-v"选项来显示Java虚拟机的参数信息，使用"-m"选项来显示传递给主类的参数信息。
     *
     * */

    /**
     * jstat [ generalOption | outputOptions vmid [interval[s|ms] [count]] ]
     * <p>
     * jstat: 查看JVM统计信息
     * jstat -class 65372 1000 10     ，类装载信息，每一秒打印一次，打印十次
     * jstat -class -t 65372 1000 10     ,-t:程序执行的总时间 ,65372: 进程ID  -class：打印类装载信息
     * jstat -class -t -h3 65372 1000 10   ,-h3:每隔三条数据打印一下表头
     * <p>
     * 其中，generalOption 用于指定一些通用的参数，例如 -classpath、-help 等等；outputOptions 用于指定输出格式，
     * 例如 -gcutil、-gcnew 等等；vmid 是 JVM 的进程 ID 或者主机名，用于指定要监控的 JVM 实例；interval 是输出间隔时间，count 是输出次数。
     * <p>
     * jstat 命令可以显示以下信息：
     * <p>
     * class：类加载、卸载的数量、时间等
     * compiler：即时编译器的编译情况
     * gc：垃圾回收器的行为和性能数据
     * gccapacity：堆内存和永久区内存的容量、使用情况和占比
     * gcutil：垃圾回收器的堆内存和永久区内存的使用情况和占比
     * printcompilation：已编译的方法
     * process：进程的状态、执行时间等
     * vm：JVM 的整体情况、线程、类、JNI、GC 等
     */
    /**
     * jstat -gc PID 1000
     * <p>
     * S0C         S1C         S0U         S1U          EC           EU           OC           OU          MC         MU       CCSC      CCSU     YGC     YGCT     FGC    FGCT     CGC    CGCT       GCT
     * 0.0     16384.0         0.0      5045.5    1294336.0     393216.0     786432.0      63684.7    74432.0    73757.6    7744.0    7474.0  21918   165.827     6     2.154     0     0.000   167.981
     * <p>
     * soc:幸存这0区容量
     * s1c ：幸存者1区容量
     *
     * sou: 幸存者o区使用
     * edgen： 伊甸园区容量
     * eu：伊甸园区使用
     * oc: 老年代总容量
     * ou：老年待使用
     *
     *
     * -Xms10m -Xmx10m -XX:SurvivorRatio=8 -XX:NewRatio=2
     * jstat -gc 82682 1000
     * jstat -gcutil 82682 1000
     * jstat -gccause  82682 1000
     *  jstat -gc -t  82682 1000 :-t ，会展示程序运行时间
      * jstat :查看jvm统计信息
     *
     */

    /**
     * jinfo: 查看虚拟机的配置参数信息，也可以用于调整虚拟机的配置参数
     * jinfo：
     * 查看系统的配置：
     * jinfo -sysprops 82682 :展示所有的系统信息
     * jinfo -flags 82682  : 展示所有的配置的参数
     * jinfo -flag UseG1GC 82682 ， 查看某一个参数的配置
     *
     * 修改系统的配置：（只可以修改部分参数，并不是所有的参数都可以修改）
     * jinfo -flag +PrintGCDetails PID  ,修改PrintGCDetails的值（对于boolean类型的参数修改）
     * jinfo -flag MaxHeapFreeRatio=100 PID， 修改MaxHeapFreeRatio的值（对于数据值类型的修改）
     *
     *
     * */

    /**
     * jmap:导出内存映像文件，内存使用情况
     * jmap -dump
     * 内存映像文件：heap dump 又叫做堆转储文件，一个Java进程在某个时间点的内存快照，
     *jmap -dump:live,format=b,file=/Users/zzh/Desktop/1.hprof 82682 ,手动导出内存快照文件，并保存
     *
     * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/zzh/Desktop/1.hprof ,自动导出dump文件，当发成堆内存溢出的时候
     *
     * jmap -heap PID:
     * jmap -histo PID： 展示类的实例信息和数量
     * */
    /**
     * jhat：与jmap配合说用，用于分析jmap生成的heap dump文件，jhat内置有一个微型的HTTP/HTML服务器，生成的dump文件的分析结果后，用户可以在浏览器中查看分析结果
     * jdk9和jdk10中删除了
     * */

    /**
     * jstack:打印jvm线程快照
     * jstack -l PID：打印锁的附加信息
     * jstack -e PID: 打印线程的附加信息
     * */
    /**
     * jcmd:
     * jcmd -l :列出所有的进程
     * jcmd PID -help ；列出所有的可以执行的命令
     * jcmd 82682 VM.info ：执行命令
     * */
    public static void main(String[] args) {
        System.out.println("jstat 命令测试");
        Scanner scanner = new Scanner(System.in);


        System.out.println(scanner.next());

    }

}


