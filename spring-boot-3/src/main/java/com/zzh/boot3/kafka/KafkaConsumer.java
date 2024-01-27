package com.zzh.boot3.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/25 10:07
 */
@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "test",groupId = "testConsumerGroup")
    public void consumer(List<String> message) {
        log.info("consumer={}", message);
    }
}
