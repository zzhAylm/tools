package com.zzh.boot3.event;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/31 15:24
 */
@Slf4j
@Component
public class SpringBoot3ApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
      log.info("args={}", JSONUtil.toJsonStr(args));
    }
}
