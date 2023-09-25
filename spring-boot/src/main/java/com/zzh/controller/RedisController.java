package com.zzh.controller;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/9/25 11:45
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/{key}/{value}")
    public void set(@PathVariable String key, @PathVariable String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @GetMapping("/{key}")
    public String get(@PathVariable String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @PostMapping("/list/{key}/{value}")
    public void list(@PathVariable String key, @PathVariable String value) {
//        redisTemplate.opsForList().set(key, 0, value);
        redisTemplate.opsForList().rightPush(key, value);
    }

    @GetMapping("/list/{key}")
    public List<String> list(@PathVariable String key) {
        Long size = redisTemplate.opsForList().size(key);
        System.out.println(size);
        return redisTemplate.opsForList().range(key, 0, size-1);
    }
}
