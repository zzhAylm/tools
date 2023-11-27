package com.zzh;

import com.zzh.constant.RabbitMqConstant;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/11/25 11:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitTest {
    private static final Logger log = LoggerFactory.getLogger(RabbitTest.class);


    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String QUEUE_NAME = "queue.zzh-test";

    @Test
    public void rabbitTest() {
        String msg = "zzh";
        rabbitTemplate.convertAndSend(QUEUE_NAME, msg);
        log.info("rabbit is send a message {}", msg);
    }

    @Test
    public void rabbitFanoutTest() {
        String msg = "zzh";
        rabbitTemplate.convertAndSend(RabbitMqConstant.FANOUT_EXCHANGE, "", msg);
        log.info("rabbit is send a message {}", msg);
    }

    @Test
    public void rabbitDirectTest() {
        String msg = "zzh";
        rabbitTemplate.convertAndSend(RabbitMqConstant.DIRECT_EXCHANGE, "key1", msg);
        log.info("rabbit is send a message {}", msg);
    }

    @Test
    public void rabbitDirectTest2() {
        String msg = "zzh";
        rabbitTemplate.convertAndSend(RabbitMqConstant.DIRECT_EXCHANGE, "key2", msg);
        log.info("rabbit is send a message {}", msg);
    }

    @Test
    public void rabbitDirectTest3() {
        String msg = "zzh";
        rabbitTemplate.convertAndSend(RabbitMqConstant.DIRECT_EXCHANGE, "zzh", msg);
        log.info("rabbit is send a message {}", msg);
    }


    @Test
    public void rabbitTopicTest3() {
        String msg = "zzh";
        rabbitTemplate.convertAndSend(RabbitMqConstant.TOPIC_EXCHANGE, "name", msg);
        log.info("rabbit is send a message {}", msg);
    }


    @Test
    public void messageConvert() {
        String msg = "zzh";
        rabbitTemplate.convertAndSend(RabbitMqConstant.QUEUE, msg);
        log.info("rabbit is send a message {}", msg);
    }
}
