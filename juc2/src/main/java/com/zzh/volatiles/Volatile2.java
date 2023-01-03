package com.zzh.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * @Description: volatile 不保证原子性
 * @Author: zzh
 * @Crete 2023/1/3 21:15
 */
public class Volatile2 {

    public static void main(String[] args) throws InterruptedException {
        Num num=new Num();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    num.add();
                }
            },String.valueOf(i)).start();
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println(num.num);

    }

    static class Num {
        private volatile Integer num = 0;


        public  void add() {
            num++;
        }

    }
}
