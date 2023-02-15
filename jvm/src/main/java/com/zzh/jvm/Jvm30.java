package com.zzh.jvm;

import java.lang.ref.SoftReference;

/**
 * @Description: 引用
 * @Author: zzh
 * @Crete 2023/2/10 16:06
 */
public class Jvm30 {


    // 强，软，弱，虚

    // 强软弱虚的区别，具体地使用场景？
    // 强引用：都不回进行回收   99%的场景
    // 软引用：内存足够的情况不回收，内存不够的情况进行回收 缓存场景
    // 弱引用：只要有垃圾回收，就会回收   缓存场景
    // 虚引用：完全不会有影响 对象是否回收，唯一的目的就是为了在对象被回收的时候可以收到一个系统的通知，对象回收的跟踪情况
    /**
     * 强引用：不回收，引用的作用域还是生效的，就不会回收
     * */

    /**
     * 软引用：soft reference , 内存不足即回收，内存敏感的缓存
     * */

    /**
     * 弱引用：
     *
     * */
    /**
     * 虚引用：
     */
    public static void main(String[] args) {


        //软引用
        SoftReference<User> softReference = new SoftReference<>(new User(18, "zzh"));
        System.out.println(softReference.get());
    }


    public static class User {
        private Integer age;
        private String name;
        public User() {
        }
        public User(Integer age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
