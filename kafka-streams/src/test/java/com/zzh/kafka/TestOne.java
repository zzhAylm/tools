package com.zzh.kafka;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/11/28 10:45
 */
@Slf4j
@SpringBootTest(classes = KafkaStreamApplication.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration

public class TestOne {
    private final Logger logger = LoggerFactory.getLogger(TestOne.class);

    @Test
    public void test() {
        logger.info("testOne is running!");

    }
}
