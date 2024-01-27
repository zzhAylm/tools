package com.zzh.boot3.service;

import io.micrometer.core.annotation.Counted;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/26 22:08
 */
@Service
public class MetricsService {

    @Counted(value = "MetricsService_count",description = "MetricsService_count")
    public void count(){

    }
}
