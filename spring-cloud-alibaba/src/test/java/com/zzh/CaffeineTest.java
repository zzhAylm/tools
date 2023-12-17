package com.zzh;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/12/16 17:52
 */
public class CaffeineTest {

    private static final Logger logger = LoggerFactory.getLogger(CaffeineTest.class);

    private final Cache<String, Object> caffeineCache = Caffeine.newBuilder().build();

    @Test
    public void caffeineTest() {
        caffeineCache.put("zzh", "zzh");
        caffeineCache.put("ylm", "ylm");
        List<String> keys = new ArrayList<>();
        keys.add("zzh");
        keys.add("ylm");
        caffeineCache.getAll(keys, (strings) ->
        {
            logger.info("strings is {}", strings);
            return new TreeMap<>();

        });
        String zzh = (String) caffeineCache.get("zzh", (key) -> "zzh");
        logger.info("value={}", zzh);
    }
}
