package com.zzh.guava;

import com.google.common.base.Joiner;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/9/22 10:38
 */
public class Guava1 {

    public static void main(String[] args) {
        System.out.println(Joiner.on(";").skipNulls().join("zzh", "ylm", "meme", "mmmi"));

//        Joiner.on(";").skipNulls().appendTo("zzh", new Object[]{})
    }
}
