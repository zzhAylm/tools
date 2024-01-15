package com.zzh.boot3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/14 17:41
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("/test")
    public void test() {
        log.info("test ={}", this);
    }
}
