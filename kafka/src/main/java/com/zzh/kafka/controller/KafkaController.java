package com.zzh.kafka.controller;

import jakarta.annotation.Resource;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/15 15:27
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {


    @Resource
    KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/send/{msg}")
    public String send(@PathVariable String msg) {
        kafkaTemplate.send("test", msg);
        return "success";
    }

}
