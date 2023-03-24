package com.zzh.jvm;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/3/14 08:44
 */
public class Jvm37 {
    // 监控相关的图形化界面
    //jdk自带的：jconsole , jvisualv ,jmc
    //三方的：jprofiler,mat,arthas,btrace
    /**
     * 图形化界面：jconsole
     * jconsole
     */
    /**
     * Visual VM：
     *
     * jvisualvm
     * */
    /**
     * MAT：
     * histogram ：展示类的信息（实例个数），深堆和浅堆的大小
     *
     * stack: 线程名，线程下的对象，对象的大小
     *
     * */

    /**
     * 浅堆： 一个对象的浅堆大小，与具体的value实际取值无关，只与引用的大小有关系
     *
     * 保留级：回收某一个对象时，可以回收的所有对象的集合（只被回收对象所引用的对象集合）
     *
     * 深堆：包含引用和引用指向的value的大小（保留级中所有的对象总和）
     *
     *
     * */

    /**
     *
     * -XX:+HeapDumpBeforeFullGC -XX:+HeapDumpPath=/Users/zzh/Company/files/dump
     *
     * -XX:+HeapDumpOutOfMemory -XX:+HeapDumpPath=/Users/zzh/Company/files/dump
     * */


    /**
     * 支配树： 某个对象A，只能被对象B访问到（如果你要访问对象A，只能经过B去访问），B支配A
     * 如果我们回收对象C，只能回收对象C，和C所支配的对象
     * */

    /**
     * tomcat内存溢出的分析：
     * 导出dump文件，在mat中分析
     * 找到大对象，在对象中找到真实存储的数据的对象，
     * 通过 sql语句查找所有的对象，根据创建时间和结束时间，计算储对象的创建速度
     * 如果是速度过快，且占用大量的堆内存，大概率就是应该 短时间内创建了多个大对象导致内存溢出
     * */

    /**
     * 内存泄漏：
     * 严格意义：对象不使用了但是不能回收的情况
     * 宽泛意义：由于某个对象操作不当导致生命周期过长，而不会被回收而导致的内存泄漏
     *
     *
     * 内存泄漏的八种情况：
     * 静态集合类，将某个局部变量加入到静态集合类中，造成某个局部变量内存泄漏
     *
     * 单例模式，
     *
     * 内部类持有外部类 ： 内部类是外部类的某个属性，内部类被外部某个类调用，当想回收 持有内部类属性的类是无法完成
     *
     * 各种连接，数据库连接，网络连接，IO连接：使用完成后如果不进行 .close操作是会造成内存泄漏的
     *
     * 不合理的作用域，本来局部变量就可以解决，创建的确实实例变量
     *
     * 在哈希值，在hashSet中的某个值，加入到集合中后，改变了其中的某些值，造成其hash值变化，我们就无法获取到这个值了（无法回收），造成内存泄漏
     *
     * 缓存造成得到内存泄漏：HashMap，如果 WeakHashMap：这种map，当没有指针指向元素时（除了集合本身的key除外），那么gc的时候，就不会被垃圾回收
     * HashMap：中的元素，gc时无法回收的，容易造成内存泄漏
     *
     * 监听器和回调
     *
     *
     * 内存泄漏的案例：
     *
     * 在stack中，出站操作，，如果元素不是删除，而是指针移动，会存在不用的对象，但是还会存储再次内存中，
     * return element[size--] : 容易造成内存泄漏，我们在集合中不用的元素，需要滞空 size--；element[size]=null
     *
     * 某个自线程持有类中的某个变量，如果线程没有结束我们这个对象就无法被回收 synchronized(key) :key是某个类的静态变量
     *
     * OQL语句的支持：对堆中的对象就行查找，查询内存信息，类信息，实例信息，
     *
     * */

    /**
     * jprofiler:
     *
     * */
    /**
     * 浅堆大小：对象头+实例数据+对其填充
     * 对象头（8个字节），实例数据（字段的大小：int类型的字段4个字节，long类型8个字节，引用类型4个字节，和实际的对象大小无关只与类型有关）+对其填充
     *
     * 深堆大小：
     * 本身对象+所有只达对象的集合（只能通过本对象 可达的对象集合）的所有浅堆大小的和
     *
     * */

    /**
     * Arthas:
     * 图形话界面的分析需要远程连接， 开通权限
     *
     * arthas：命令行交互式模式，在线排查问题，无需重启，动态跟踪Java代码，实时监控JVM状态
     *
     * 启动：jar -jar arthas-boot.jar PID
     * dashboard
     * thread
     *
     * jad demo.MathGame  : 通过 jad 来反编译 Main Class
     *
     * 基础指令：help,cat ,echo ,grep ,tee,pwd,cls ,session,history,quit ,stop,keymap
     * Jvm相关指令：
     * class加载相关指令：
     * sc -d java.lang.String： 展示类
     * sm -d java.lang.String toString ： 展示类的方法
     *
     * jad: 反编译已经加载的类的源码，可以是类或者方法
     *
     * mc - 内存编译器，内存编译.java文件为.class文件
     * mc -d /tmp/output /tmp/ClassA.java /tmp/ClassB.java
     * redefine - 加载外部的.class文件，redefine 到 JVM 里
     *
     * jad --source-only com.example.demo.arthas.user.UserController > /tmp/UserController.java
     * mc /tmp/UserController.java -d /tmp
     * redefine /tmp/com/example/demo/arthas/user/UserController.class
     *
     *
     * retransform - 加载外部的.class文件，retransform 到 JVM 里
     *
     * jad --source-only com.example.demo.arthas.user.UserController > /tmp/UserController.java
     * mc /tmp/UserController.java -d /tmp
     * retransform /tmp/com/example/demo/arthas/user/UserController.class
     *
     * monitor ,watch,
     * */
    public static void main(String[] args) {


        String str="";


    }
}
