package com.zzh.sync;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/10/27 09:32
 */
public class Test03 {

    public static ThreadLocal<String> threadLocal=new InheritableThreadLocal<>();

    public static void main(String[] args) {
        ConcurrentSkipListMap<String,String> skipListMap=new ConcurrentSkipListMap<>();
        skipListMap.put("1", "1");
        skipListMap.forEach((k,v)-> System.out.println(k+"="+v));


        threadLocal.set("sss");
    }
}
