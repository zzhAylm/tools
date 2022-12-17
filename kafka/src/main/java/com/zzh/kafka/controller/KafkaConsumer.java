package com.zzh.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/15 16:22
 */
@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"test"})
    public void consumer(String msg){
      log.info("消费kafka的消息={}",msg);

    }
}
