package com.zzh.jdk8;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/11/2 17:49
 */
public class jdk10 {

    public static void main(String[] args) throws MalformedURLException {
        var name=10;
        var id = 0;
        var codefx = new URL("https://mp.weixin.qq.com/");
//        var list = new ArrayList<>();
        var list = List.of(1, 2, 3);
        var map = new HashMap<String, String>();
//        var p = Paths.of("src/test/java/Java9FeaturesTest.java");
//        Paths.get(, )
        var numbers = List.of("a", "b", "c");
        for (var n : list)
            System.out.print(n+ " ");

//        var string ={0,1,2,3,46};
     }
}
