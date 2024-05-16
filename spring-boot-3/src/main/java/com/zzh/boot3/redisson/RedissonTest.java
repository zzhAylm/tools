package com.zzh.boot3.redisson;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RKeys;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/4/21 15:55
 */
@Slf4j
@SpringBootTest
public class RedissonTest {


    @Resource
    private RedissonClient redisson;


    @Test
    public void test(){
        RLock rLock = redisson.getLock("zzh");
        try {
            rLock.lock(10, TimeUnit.SECONDS);
            log.info("获取锁成功");
        } finally {
            rLock.unlock();
        }
    }

    @Test
    public void testKey(){
        RKeys keys = redisson.getKeys();
        Iterable<String> allKeys = keys.getKeys();
        Iterable<String> foundedKeys = keys.getKeysByPattern("key*");
        long numOfDeletedKeys = keys.delete("obj1", "obj2", "obj3");
        long deletedKeysAmount = keys.deleteByPattern("test?");
        String randomKey = keys.randomKey();
        long keysAmount = keys.count();
    }
}
