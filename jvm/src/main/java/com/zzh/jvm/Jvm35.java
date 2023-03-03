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
    public static void main(String[] args) {

    }
}
