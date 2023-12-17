package com.zzh.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/12/14 15:00
 */

@Slf4j
@Component
public class Target {

    public void test(){
        log.info("test target method!");
    }


}
