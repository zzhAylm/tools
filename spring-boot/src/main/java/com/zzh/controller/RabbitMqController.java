package com.zzh.controller;

import com.zzh.constant.RabbitMqConstant;
import com.zzh.dto.ResponseDto;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/11/23 21:51
 */
@RestController
@RequestMapping("/rabbit")
public class RabbitMqController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/{msg}")
    public ResponseDto<String> sendMsg(@PathVariable String msg) {
        rabbitTemplate.send(RabbitMqConstant.EXCHANGE,RabbitMqConstant.QUEUE, new Message(msg.getBytes()));
        return ResponseDto.success(msg);
    }



}
