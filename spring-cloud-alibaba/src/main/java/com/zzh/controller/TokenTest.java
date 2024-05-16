package com.zzh.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/3/26 14:11
 */
@Slf4j
@RestController
@RequestMapping("/token")
public class TokenTest {

    @PostMapping("/test")
    public User token(@RequestBody User user) {
        log.info("user is {}", user.toString());
        return user;
    }

    @PostMapping("/verify")
    public User verify(@RequestBody User user) {
        log.info("user is {}", user.toString());
        return user;
    }

    @Data
    public static class User{
        String name;
        private String age;
    }
}
