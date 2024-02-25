package com.zzh.boot3.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/2/6 10:29
 */
@Slf4j
@SpringBootTest
public class ThreadPoolTest {

//    乍一看，scheduleAtFixedRate和scheduleWithFixedDelay没啥区别，实际它们还是有区别的：
//
//    scheduleAtFixedRate按照固定速率执行任务，比如每5秒执行一个任务，即使上一个任务没有结束，5秒后也会开始处理新的任务；
//
//    scheduleWithFixedDelay按照固定的时延处理任务，比如每延迟5秒执行一个任务，无论上一个任务处理了1秒，1分钟还是1小时，下一个任务总是在上一个任务执行完毕后5秒钟后开始执行。

    @Test
    public void threadPoolTest() throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        // 五秒后执行
        scheduledExecutorService.schedule(() -> log.info("schedule"), 5, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            log.info("每五秒执行一次");
        }, 1, 5, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                log.info("scheduleWithFixedDelay begin");
                Thread.sleep(1000 * 10);
                log.info("scheduleWithFixedDelay end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 1, 5, TimeUnit.SECONDS);
        Thread.sleep(1000 * 50);
    }

    @Test
    public void threadPoolApiTest() {
        log.info("核数={}", Runtime.getRuntime().availableProcessors());
    }
}
