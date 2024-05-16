package com.zzh.boot3;

import cn.hutool.json.JSONUtil;
import com.zzh.boot3.service.TestService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.server.PathContainer;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/14 18:13
 */
@Slf4j
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private TestService testService;

    @Test
    public void test() {
        log.info("test={}", testService);
    }

    @Test
    public void testFactoryBean() {
        Object testFactoryBean = testService.getBean("testFactoryBean");
        log.info("testFactoryBean ={}", testFactoryBean.toString());
    }

    @Test
    public void jsonTest() {
        String json = "{\"reqNo\":\"WePS7\",\"timestamp\":\"2024-03-15 10:10:10.123\",\"data\":{\"name\":\"石子明\",\"age\":18,\"address\":\"beijngshi\"}}";
        System.out.println(json);
        System.out.println(JSONUtil.parseObj(json).toJSONString(2));
        System.out.println(JSONUtil.toJsonStr(json));
    }

    @Resource
    private RedissonClient redissonClient;

    @Test
    public void redissonTest() throws InterruptedException {
        RBlockingQueue<Object> blockingQueue = redissonClient.getBlockingQueue("zzh");
        blockingQueue.add("zzh");
        blockingQueue.add("ylm");
        blockingQueue.add("zzhYlm");
        blockingQueue.add("zzh");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());


        RLock rLock = redissonClient.getLock("zzh");

        rLock.lock(100, TimeUnit.SECONDS);

        Thread.sleep(1000 * 5);
        rLock.unlock();

        RDelayedQueue<Object> delayedQueue = redissonClient.getDelayedQueue(redissonClient.getBlockingQueue("zzh"));


        delayedQueue.offer("zzh", 100, TimeUnit.SECONDS);

        System.out.println(delayedQueue.poll());

    }

    @Test
    public void testStr() {
        String str = "安徽省铜陵市公安局交通警察支队安徽省铜陵市公安局交通警察支队";
        String sub = str.substring(str.length() / 2);
        System.out.println(str.replace(sub, ""));
    }


    @Resource
    private PathPatternParser patternParser;


    @Test
    public void path() {

        String noticeName = "/zzh";

        List<String> patterns = List.of("/test", "/zzh", "/ylm", "/zzh/**","/zzh/*");

        String match = patterns.stream()
                .filter(path -> patternParser.parse(path).matches(PathContainer.parsePath(noticeName)))
                .min(Comparator.comparing(path -> patternParser.parse(path))).orElse(null);
        System.out.println(match);
    }
}
