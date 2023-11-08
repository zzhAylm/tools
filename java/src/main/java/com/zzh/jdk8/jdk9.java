package com.zzh.jdk8;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/11/2 09:41
 */
public class jdk9 {


    public static void main(String[] args) {
        Stream<String> stringStream = Stream.ofNullable("Java");
        System.out.println(stringStream.count());// 1
        Stream<String> nullStream = Stream.ofNullable(null);
        System.out.println(nullStream.count());//0
        Stream.builder().add("aaa").add("bbb").build().forEach(System.out::println);

        // 获取满足的，碰到不满足的就停止
        List<Integer> integerList = List.of(11, 33, 66, 8, 9, 13);
        integerList.stream().takeWhile(x -> x < 50).forEach(System.out::println);// 11 33

        // 删除不满足的，碰到满足的就停止
        List<Integer> integerList2 = List.of(11, 33, 66, 8, 9, 13);
        integerList2.stream().dropWhile(x -> x < 50).forEach(System.out::println);// 66 8 9 13

        // 使用原始 iterate() 方法输出数字 1~10
        Stream.iterate(1, i -> i + 1).limit(10).forEach(System.out::println);
// 使用新的 iterate() 重载方法输出数字 1~10
        Stream.iterate(1, i -> i <= 10, i -> i + 1).forEach(System.out::println);

//        public Optional<T> or(Supplier<? extends Optional<? extends T>> supplier)

        Optional<Object> objectOptional = Optional.empty();
        objectOptional.or(() -> Optional.of("java")).ifPresent(System.out::println);//java

        // 获取当前正在运行的 JVM 的进程
        ProcessHandle currentProcess = ProcessHandle.current();
// 输出进程的 id
        System.out.println(currentProcess.pid());
// 输出进程的信息
        System.out.println(currentProcess.info());
    }
}
