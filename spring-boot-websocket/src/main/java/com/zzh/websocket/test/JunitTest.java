package com.zzh.websocket.test;

import com.zzh.websocket.proxy.AspectProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/13 17:11
 */
@Slf4j
@SpringJUnitWebConfig
@WebAppConfiguration
@TestPropertySource(properties = "test.engine = testng")
public class JunitTest {

    @Autowired
    private AspectProperties aspect;

    @Test
    public void test(){

        log.info("junit test."+aspect.toString());

    }
}
