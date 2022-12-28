package com.zzh.juc2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Description: 电商价格统计问题
 * @Author: zzh
 * @Crete 2022/12/26 13:38
 */
public class Juc6 {

    static List<Price> priceList = Arrays.asList(
            new Price("淘宝"),
            new Price("京东"),
            new Price("天猫"),
            new Price("拼多多"),
            new Price("当当网")
    );
    //手动创建线程池
    static ExecutorService executorService = new ThreadPoolExecutor(
            2,
            4,
            10L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100));

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        getPriceOneByOne("mysql").forEach(System.out::println);
        long end = System.currentTimeMillis();
        System.out.println("时间花费：" + (end - begin));

        long begin1 = System.currentTimeMillis();
        try {
            getPriceCompletableFuture("mysql").forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
        long end1 = System.currentTimeMillis();
        System.out.println("时间花费：" + (end1 - begin1));
    }

    //获取 价格
    public static List<String> getPriceOneByOne(String productName) {

        return priceList.stream().map(price ->
                {
                    try {
                        return String.format("平台：%s，产品：%s,价格：%.2f", price.platform, productName, Price.getPrice());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).toList();

    }

    public static List<String> getPriceCompletableFuture(String productName) {
        return priceList.stream().map(price -> CompletableFuture.supplyAsync(() -> {
                    try {
                        return String.format("平台：%s，产品：%s,价格：%.2f", price.platform, productName, Price.getPrice());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })).toList()
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Price {
        private String platform;
        private String name;
        private Double price;

        public Price(String platform) {
            this.platform = platform;
        }

        public static Double getPrice() throws InterruptedException {
            TimeUnit.SECONDS.sleep(1);
            return new Random().nextDouble();
        }
    }


}
