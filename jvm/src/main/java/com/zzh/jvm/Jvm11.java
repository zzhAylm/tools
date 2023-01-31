package com.zzh.jvm;

/**
 * @Description: 本地方法接口，本地方法库
 * @Author: zzh
 * @Crete 2023/1/30 14:52
 */
public class Jvm11 {

    //  本地方法，使用native标识，没有方法体，像是一个抽象方法
    //  为什么要使用Native方法？
        // 有时Java应用需要与Java外面的环境交互，这是本地方法存在的主要原因
        // 与操作系统交互，调用底层你操作系统，操作系统是由c和c++实现的，使用c和c++调用效率更高一些
        // Sun的解析器是由C实现的，这是的他想一些普通的c一样与外部交互
    public static void main(String[] args) {

    }
}
