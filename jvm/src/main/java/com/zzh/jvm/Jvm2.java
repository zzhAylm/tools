package com.zzh.jvm;


/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/28 10:00
 */
public class Jvm2 {

    //jvm: 类加载器子系统：
    // 类加载过程概述： 加载阶段，选择阶段，初始化阶段
    // 加载到方法区，除了类的行信息外，还包括运行时常量池中的数据（字符串字面值和数字常量）
    public static void main(String[] args) {

        // 获取类加载器
//        ClassLoader classLoader = Jvm1.class.getClassLoader();

        // 物理磁盘的。class文件加载到方法区内存中是以二进制流的形式加载的

        // 验证，准备，解析

        // 被final修饰的变量，为常量，在编译阶段就会分配吗，准备阶段会显示的初始化

        // 字节码文件的结构：开头是 cafe baby ,

        //

    }

    static {


    }


}
