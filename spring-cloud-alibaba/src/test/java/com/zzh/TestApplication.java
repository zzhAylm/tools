package com.zzh;

import com.zzh.aop.Target;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/12/14 15:04
 */
@Slf4j
@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootAlibabaApplication.class)
public class TestApplication {

    @Resource
    private Target target;

    @Test
    public void test(){
        target.test();
    }
}
