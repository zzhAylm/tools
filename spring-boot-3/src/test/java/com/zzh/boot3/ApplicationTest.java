package com.zzh.boot3;

import com.zzh.boot3.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/14 18:13
 */
@Slf4j
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private TestService testService;

    @Test
    public void test(){
        log.info("test={}",testService);
    }
}
