package com.zzh.jdk8;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/11/2 19:39
 */
public class Jdk17 {


    public static void main(String[] args) {

        RandomGeneratorFactory<RandomGenerator> l128X256MixRandom = RandomGeneratorFactory.of("L128X256MixRandom");
// 使用时间戳作为随机数种子
        RandomGenerator randomGenerator = l128X256MixRandom.create(System.currentTimeMillis());
// 生成随机数
        for (int i = 0; i < 100; i++) {
            System.out.println(randomGenerator.nextInt(10));

        }




    }

    // Old code
    static String formatter(Object o) {
        String formatted = "unknown";
        if (o instanceof Integer i) {
            formatted = String.format("int %d", i);
        } else if (o instanceof Long l) {
            formatted = String.format("long %d", l);
        } else if (o instanceof Double d) {
            formatted = String.format("double %f", d);
        } else if (o instanceof String s) {
            formatted = String.format("String %s", s);
        }
        return formatted;
    }

//    // New code
//     String formatterPatternSwitch(Object o) {
//        return switch (o) {
//            case Integer i -> String.format("int %d", i);
//            case Long l    -> String.format("long %d", l);
//            case Double d  -> String.format("double %f", d);
//            case String s  -> String.format("String %s", s);
//            default        -> o.toString();
//        };
//    }

    // Old code
    static void testFooBar(String s) {
        if (s == null) {
            System.out.println("oops!");
            return;
        }
        switch (s) {
            case "Foo", "Bar" -> System.out.println("Great");
            default           -> System.out.println("Ok");
        }
    }

//    // New code
//    static void testFooBar(String s) {
//        switch (s) {
//            case null         -> System.out.println("Oops");
//            case "Foo", "Bar" -> System.out.println("Great");
//            default           -> System.out.println("Ok");
//        }
//    }
}
