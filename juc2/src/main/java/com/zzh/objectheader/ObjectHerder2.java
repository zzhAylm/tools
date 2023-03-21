package com.zzh.objectheader;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/8 21:53
 */
public class ObjectHerder2 {

    //Object 对象的组成：
    //对象头：
        // 对象标记 ： 8个字节
        // 类型指针： 如果开启指针压缩是4个字节，没有开启则是8个字节
    //实例数据： 根据对象类型的属性大小确定
    //对齐填充： 不够八个字节的整数倍，就填充字节使其是八个字节的整数倍
    public static void main(String[] args) {
        // 16个字节
        Object o=new Object();

        String str=new String();

        // 8（对象标记）+4（类型指针）+4（number int类型4字节）+1（boolean类型1字节）+7（对齐填充7字节）=24 个字节
        Book book=new Book();

        // 对象标志：其中有 4 位标志的是对象的分代年龄，所以对象的分代年龄最大是15
    }
    static class Book{
        int number; //4 字节
        boolean flag;//1 字节

    }
}
