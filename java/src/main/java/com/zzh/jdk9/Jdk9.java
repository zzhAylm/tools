package com.zzh.jdk9;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/3/12 11:47
 */
public class Jdk9 {

    public static void main(String[] args) throws IOException, InterruptedException {

        Set<String> set = new HashSet<>();

        InputStreamReader reader = new InputStreamReader(System.in);
        try (reader) {
            reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         *
         * String存储结构变更
         * Java 9 之前，字符串的底层是用char[]进行存储的，Java 9 中，字符串改用成了byte[]进行存储:
         *
         * QQ截图20190214101945.png
         *
         * 之所以做出这个改变是因为：大多数String类型对象存储的都是拉丁字符，这些字符只占一个字节，而char能够存储两个字节，所以大部分情况下都浪费了一半的存储空间。
         * Java 9 将String类的内部表示从UTF-16的char数组更改为byte数组加上encoding-flag字段。
         * 新String类将根据字符串的内容存储编码为ISO-8859-1 / Latin-1（每个字符一个字节）或UTF-16（每个字符两个字节）的字符。
         * encoding-flag编码标志将指示使用哪种编码。
         *
         * 基于String的类如StringBuffer和StringBuilder等也是做出了相同的改变。
         *
         * */

        List<Integer> list = List.of(1, 2, 3);
        Set<Integer> set1 = Set.of(1, 2, 3);
        Map<String, Object> map1 = Map.of("mrbird", 18, "kangkang", 20);

        List<Integer> list2 = Arrays.asList(45, 43, 76, 87, 42, 77, 90, 73, 67, 88);
        //程序从头开始判断当前值是否小于50，当判断到第三个元素76时，发现不满足，于是程序就结束了。所以takeWhile不同于过滤器。
        list2.stream().takeWhile(x -> x < 50).forEach(System.out::println);
        list2.stream().dropWhile(x -> x < 50).forEach(System.out::println);

        // Java 8 中的
        Stream.iterate(0, x -> x + 1).limit(10).forEach(System.out::println);
        // 等价于 Java 9 中的
        Stream.iterate(0, x -> x < 10, x -> x + 1).forEach(System.out::println);


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder(URI.create("https://mrbird.cc"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.version().name());
        System.out.println(response.body());
    }

    public void test(){
        List<String> list = new ArrayList<>();
        list.add("mrbird");
        list.add("kangkang");
        list.add("maria");



        Optional<List<String>> optional = Optional.of(list);
        List<List<String>> collect = optional.stream().toList();
        // 流中的元素为 list
        Stream<List<String>> stream = optional.stream();
        stream.forEach(System.out::println);

        // 流中的元素为 list中的每个元素
        Stream<String> stringStream = optional.stream().flatMap(strings -> strings.stream());
        stringStream.forEach(System.out::println);
    }
}
