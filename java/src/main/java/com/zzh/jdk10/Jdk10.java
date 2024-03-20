package com.zzh.jdk10;

import java.util.ArrayList;

/**
 * @Description: var 局部变量的使用
 * @Author: zzh
 * @Crete 2024/3/12 11:46
 */
public class Jdk10 {
    public static void main(String[] args) {
        var arrayList = new ArrayList<>();

        var list = new ArrayList<>();
        list.add("hello");
        list.add("java 10");
        list.add(10);

        for (var s : list) System.out.println(s);


    }
}
