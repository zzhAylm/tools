package com.zzh.boot3.kafka;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/25 10:12
 */
@Slf4j
@SpringBootTest
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;


    @Test
    public void sendMessage(){
        String message="zzh";
        CompletableFuture<SendResult<String, String>> completableFuture = kafkaTemplate.send("test", message);
        completableFuture.whenComplete((k,v)-> log.info("发送成功，message={}, k={},value={}", message,k,v));
    }
}
