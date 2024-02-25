package com.zzh.boot3.juc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Exchanger;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/2/6 11:12
 */
@Slf4j
@SpringBootTest
public class JucTest {

    @Test
    public void exchangerTest(){
        final Exchanger<String> exchanger = new Exchanger<>();
        new Thread(() -> {
            System.out.println("thread1开始");
            try {
                String exchange = exchanger.exchange("来自thread1的数据");
                System.out.println("接收thread2发送的数据：" + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread1结束");
        }, "thread1").start();

        new Thread(() -> {
            System.out.println("thread2开始");
            try {
                String exchange = exchanger.exchange("来自thread2的数据");
                System.out.println("接收thread1发送的数据：" + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread2结束");
        }, "thread2").start();
    }
}
