package com.zzh.boot3;

import com.zzh.boot3.service.TestService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/21 15:35
 */
@Slf4j
@SpringBootTest
public class SpringBootApplicationTest {


    @Resource
    private TestService testService;

    @DisplayName("test 测试")
    @Test
    public void applicationTest(){
        testService.test();
        Assertions.assertEquals("1", "1");
        log.info("application test");
    }

    @BeforeAll
    public static void beforeAll(){
        log.info("before all!");
    }


}
