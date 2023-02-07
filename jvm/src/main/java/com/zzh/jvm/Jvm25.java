package com.zzh.jvm;

/**
 * @Description: 字符串详情
 * @Author: zzh
 * @Crete 2023/2/5 18:46
 */
public class Jvm25 {

    // StringTable:

    //String 类型，jdk8 底层是char数据，jdk9以后底层是byte数组
    // == 判断地址是否相等
    // equals() : 判断地址是否 相等然后在判断值是否相等
    // String 的不可变性，在字符串常量池中创建的字符串都不会改变

    //字符串常量池是不会存储相同内容的字符串的
    // -XX:StringTableSize=xxxx 设置字符串常量池的大小

    // StringTable放在字符串常量池，放在堆中，八种基本数据类型

    // 字符串常量池为什么从方法区迁移到堆中？ jdk6的时候永久代的内存比较小，容易OOM， 永久代的垃圾回收频率低，垃圾不容易回收

    // intern() ： 帮助我们在字符串常量池中 创建字符串

    /**
     * 字符串的基本操作：
     * 在字符串常量池中存在的字符串就不会再进行创建了
     * intern()：如果字符串s在字符串常量池中存在对应字面量，则intern()方法返回该字面量的地址；如果不存在，则创建一个对应的字面量，并返回该字面量的地址
     */
    public static void main(String[] args) {

        String s1 = "z" + "z" + "h";
        String s2 = "zzh";

        // 编译的时候就可以优化
        String s3="zzhzz";
        // 如果拼接中只有字符串常量，则直接在常量池中创建，如果拼接过程中有变量，则相当于在堆中创建字符串和常量池中的地址不相同

        // 拼接中有 变量无法进行编译优化
        String s4 = s1 + "zz";

        // 会查找字符串常量池中是否有 "zzhzz" , 如果有直接返回此地址，如果没有就会创建并返回
        String s5 = s4.intern();
        s3.equals(s4);//false
        s5.equals(s3);//true
        // abc  一点是放在字符串常量池中，将此地址
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

        final  String ss="zzh";
        final String sss="ylm";

        String s6=ss+sss;
        String s7="zzhylm";
        // ture , final String: 相当于字符常量和"zzh"这样一样
        System.out.println(s7 == s6);
        /**
         * String s=s1+"zzh";
         *  执行细节：
         *  StringBuilder builder=new StringBuilder();
         *  builder.append(s1);
         *  builder.append("zzh");
         *  builder.toString(); -->类似于new String();
         *
         * String s="zzh";
         * 执行细节：
         *
         * */

        /**
         * 1.字符串拼接操作不一定使用的是StringBuilder，如果拼接符号左右两边都是字符串常量或者常量引用，则仍然使用编译器优化，即非StringBuildr的方法
         * 2.针对 final 修饰的类，方法，基本数据类型，引用数据类型的量的结果时，能是用上final 尽量都是用final
         * */
        /**
         * StringBuilder 的append方法自始至终只创建了一个StringBuilder
         * 使用 字符串拼接的方法"+" 会创建过多StringBuilder
         *
         * StringBuilder 可以指定底层数组的长度，防止扩容浪费性能
         *
         * 字符串拼接"+" === StringBuilder append()
         *
         * */

        /**
         * intern()方法： str.intern(): 检查字符串常量池中是否有字符串和str相等，如果有则直接返回，如果没有则需要在常量池中常见此字符串，并返回给str
         *
         *
         *
         * */

        // 会创建几个对象，会创建两个，一个是堆中new String对象，另一个是ab在字符串常量池中的对象"ab"
        String ab = new String("ab");


        // 创建的对象：
        // StringBuilder  ,
        // new String（"a"），
        // new String ("b")，
        // 字符串常量池中的'a'
        // 字符串常量池中的'b'
        // builder.toString():
                // 深入剖析： builder.toString()方法：
                // new String("ab")创建的方法在常量池中有"ab"，toString()方法的调用，在字符串常量池中不会创建 "ab"，

        String ab1=new String("a")+new String("b");

        StringBuilder builder=new StringBuilder();
        builder.append("a");
        // toString()方法和new String()方法的区别，toString方法的值不会在常量池中创建字符串"ab"，new String()方法会在常量池中创建"ab"
        builder.toString();



        /**
         * 在堆中创建String（"a"） 和String（"b"）:
         * buffer.toString()：在堆中创建 new String（"ab"） ,ab不会在字符串常量池中创建，
         * str.intern(): 字符串常量池中不会创建一个字符串常量，而是创建一个引用，指向堆中的"ab"
         * str1 指向堆中的引用，str1== str
         * */
        String str=new String("a")+new String("b");
        // 字符串常量池中，不存在 "aa"
        str.intern();// 为了节省内存，不会在常量池常量池创建"aa"，而是生成一个引用指向堆中的new String("aa"); 字符串常量池和静态变量都在堆中
        String str1="ab";//  等于字符串常量中的引用，引用指向堆中的"ab"。str指向堆中的"ab"，str和str1指向的地址都是堆中的地址
        System.out.println(str==str1); // jdk6:false, jdk7:true




        /**
         * 使用intern（）方法可以大大节省内存，尤其是存在许多的重复的字符串，使用intern可以节省空间
         * 用字符串池常量池中的数据，不会在堆中创建对象了
         * */








    }
}
