package com.zzh.boot3.controller;

import com.zzh.boot3.service.MetricsService;
import jakarta.annotation.Resource;
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
@RequestMapping("/metrics")
public class MetricsController {


    @Resource
    private MetricsService metricsService;

    @GetMapping("/count")
    public void test() {
        metricsService.count();
        log.info("test ={}", this);
    }


}
