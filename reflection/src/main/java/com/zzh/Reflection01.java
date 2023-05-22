package com.zzh;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/5/10 21:33
 */
public class Reflection01 {

    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> aClass = Class.forName("com.zzh.Cat");

        Field[] fields = aClass.getFields();

        Arrays.stream(fields).forEach(field -> {
            field.setAccessible(true);
        });




//  (1)class.forName 全类名            Class<?> aClass=  Class.forName("com.zzh.Cat")
//  (2) 多用于参数传递：                 Class<?> aClass= Cat.class

        Cat cat = new Cat();

//        Class<? extends Cat> aClass1 = cat.getClass();


        ClassLoader classLoader = cat.getClass().getClassLoader();
        Class<?> aClass1 = classLoader.loadClass("com.zzh.Cat");

        Class<Integer> integerClass = Integer.class;

        Class<Integer> type = Integer.TYPE;


        // 静态加载 ： 如何没有这个类，在编译期间就会报错    举例： new Demo()
        // 动态加载 ： 如果没有这个类，但是在使用过程中，我们不会使用这个类，就不会报错，只有使用时发现没有这个类就会报错  举例：Class.forName("com.zzh.Cat")

        // 静态加载：依赖性强，如果没有就报错
        // 动态加载：依赖性弱，只有真正经过这个类的时候，才会加载这个类

    }
}
