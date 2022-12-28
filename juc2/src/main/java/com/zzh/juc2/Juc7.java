package com.zzh.juc2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/26 15:24
 */
public class Juc7 {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "zzh";
        });
        TimeUnit.SECONDS.sleep(4);
        //如果已经就算出结果，就直接返回结果，没有计算出结果就直接返回 指定的值，不阻塞
        System.out.printf("%s\n", future.getNow("ylm"));
        //对自我的一种保护，如果已经计算出结果则 complete 返回false，future.join || get 获取结果
        //如果没有计算出结果则，complete 返回 ture  ，future.join || get 获取complete设置的值
        System.out.printf("%s\n%s\n", future.complete("ylm"), future.join());




    }
}
