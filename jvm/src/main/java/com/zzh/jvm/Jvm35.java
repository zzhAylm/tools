package com.zzh.jvm;

/**
 * @Description: 字节码与类的加载篇
 * @Author: zzh
 * @Crete 2023/2/28 09:11
 */
public class Jvm35 {

    /**
     * 前端编译器：javac
     * <p>
     * javac ：将java文件编译成 .class 文件
     * <p>
     * JIT： 在执行过程过，将热点代码翻译成机器指令，缓存起来
     * AOT: 静态提前编译器，在代码执行前，将热点迪马翻译成机器指令，缓存起来，（打破了Java一次编译到处执行的规律）
     * <p>
     * <p>
     * 字节码指令： 操作码+操作数
     * <p>
     * 反编译：javap -v xx.class
     * javac xxx.java 编译
     */
    /**
     * class 文件结构组成：
     * 魔数，版本号，常量池，访问标识，类索引，父类索引，接口索引集合，字段表集合，方法表集合，属性表集合
     *
     * **/

    /**
     * 类加载过程：
     *
     * 类加载过程：将字节码文件的加载到jvm内存中，并存储为Java类模版，类模版对象
     * 只要是加载到jvm内存中的字节码文件，都会产生一个Java类模版，通过反射机制获取到类的任意信息
     * 过程：
     * 1。通过类的全类名，获取二进制数据流
     * 2。解析类的二进制数据流为方法区中的数据结果（Java 类模版）
     * 3。创建类的实例，作为方法去这个类的各种数据的访问入口
     * */
    /**
     * 类装载的初始化过程中，会执行clinit 方法，包括静态变量的初始化赋值和 静态代码块内代码的执行
     *
     * 类装载的过程》类实例的创建过程，
     * 也就是静态代码快的执行》构造方法的执行
     *
     *
     * */
    /**
     * -XX:+TraceClassLoading : 类加载过程
     * 类的主动加载和类的被动加载
     * 主动加载：会进行类的初始化，即静态代码块的执行和静态变量的显示初始化
     * 被动加载：不会进行类的初始化，类会进行加载，但不会进行初始化
     * */
    /**
     * 类加载后就会在方法区中形成一个类模版，在堆中创建一个class类的实例对象，执行方法去的中的类模版
     * 通过class类实例对象我们可以获取到 类的所有信心（方法，属性。。。）
     * Class对象还会有一个指针执行classloader ，执行加载这个类的的类加载器
     *
     * 我们创建的实例对象，实例对象会指向class类对象
     * */
    /**
     * 类的卸载过程：
     *
     *
     * */

    /**
     * 类加载器：
     * 启动类加载器
     * 扩展类加载器
     * 应用程序类或系统类加载器
     * 用户自定义加载器
     *
     * 加载过程：隐式加载和显示加载
     *
     * 同一个class 文件，被不同的类加载器加载的话，会变成不通的类
     * tomcat可以利用不同的类加载器，可以加载同一个类，来实现应用的隔离
     *
     * 所有的类加载器，都继承子ClassLoader ，classLoader内有一个属性字段，叫做 parentClassloader
     * 子类加载器和父类加载器不是单纯的继承 关系
     *
     * */

    /**
     * 启动类加载器：加载java，javax和sun包内的类，还会加载器扩展类姐载器和应用程序类加载器
     * -XX:+TraceClassLoading : 追踪类加载过程的参数
     * */
    /**
     * 扩展类加载器：
     *
     * 自动加载jre/lib/ext 目录下的类 和 系统指定的属性 java.ext.dir 执行值的目录下的文件
     *
     * */

    /**
     * 类加载器，
     * 基本数据类型是预先定义好的，不要类加载器加载
     * 数据类型的类加载器和数据元素的的类加载器一致
     * */
    /**
     * 上下文的类加载器，就是系统类加载器，（在系统类加载器创建的时候，会将环境变量设置为这个类加载器）
     *
     *  ClassLoader ： 的核心方法
     *  loadClass
     *  findClass
     *  defineClass
     *
     * */
    /**
     * loadClass()和Class.forName()的区别：
     * loadClass加载的类不会进行初始化，只是进行加载，在第一个使用的时候进行初始化
     * Class.fromName():加载的时候就会进行初始化操作Clinit
     * */
    /**
     * 双亲委派模型：
     * 避免类的重复加载，确保一个类的全局唯一性
     * 避免核心API被篡改
     * <p>
     * 缺点：子类加载器加载的类，在父类加载器的加载类中无法访问，
     * <p>
     * 在子类加载器加载的类中可以以访问父类加载器加载的类，反之则不可以
     * <p>
     * 应用类可以访问系统类，但是系统类不可以访问应用类
     * <p>
     * <p>
     * 破坏双亲委派机制：
     *
     * 第一次：没有双亲委派机制
     * 第二次：Java 提供了一些核心的API，是由引导类加载器加载，但是其实现确实有系统类加载器加载，在核心API中无法引用实现类，为了解决这个问题，引入了 】
     * 线程上下文加载器 （默认是系统类加载器），如果核心API中需要使用实现类，可以通过线程上下文加载器是调用其实现类
     * 第三次：对程序的动态性追求导致，代码热部署和模块化部署，形成模块化，每一个模块都有自己的类加载器，加载器不再呈现树状，而是呈现网状结构
     *
     * 热替换 ：在运行的程序不停止的情况，对某个类修改后，重新编译，加载，创建新的实例对象，当程序再次用到次类后就会使用新的实例对象
     *
     * 沙箱安全机制：
     * 保护程序安全，保护Java原生的JDK代码
     *
     *
     * loadClass ： 内的代码体现了双亲委派机制，底层调用了findClass
     * findClass ： 加载类文件到本地
     * 重写loadClass 可能会破坏双亲委派机制，重写findClass会保留双亲委派机制
     *
     *
     */
    /**
     * jdk9的新特性：
     *
     * 类分为多个模块，每个加载器加载不通的模块
     *
     * */
    /**
     * 命令行工具：
     * javac 编译
     * java  运行.class 文件
     * javap 反编译
     * jslookup 解析域名地址
     *
     *
     *
     * */
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
     *
     *
     */

    public static void main(String[] args) throws ClassNotFoundException {

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

        // 系统类加载器
        System.out.println(Thread.currentThread().getContextClassLoader());

        // classloader 的核心方法：loadClass()
        Class<?> aClass = systemClassLoader.loadClass("com.zzh.jvm.Jvm35");

        System.out.println(aClass.getClassLoader());


    }
}
