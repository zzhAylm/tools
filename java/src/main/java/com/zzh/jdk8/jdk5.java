package com.zzh.jdk8;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/11/1 21:00
 */
public class jdk5 {

    public static void main(String[] args) {
        Hint hint = Person.class.getAnnotation(Hint.class);
        System.out.println(hint);                   // null
        Hints hints1 = Person.class.getAnnotation(Hints.class);
        System.out.println(hints1.value().length);  // 2

        Hint[] hints2 = Person.class.getAnnotationsByType(Hint.class);
        System.out.println(hints2.length);          // 2
    }
}
