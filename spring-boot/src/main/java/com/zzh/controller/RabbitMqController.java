package com.zzh.controller;

import com.zzh.constant.RabbitMqConstant;
import com.zzh.dto.ResponseDto;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
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
        rabbitTemplate.send(RabbitMqConstant.EXCHANGE, RabbitMqConstant.QUEUE, new Message(msg.getBytes()));
        return ResponseDto.success(msg);
    }


    @GetMapping("/callback/{msg}")
    public ResponseDto<String> sendMsgReturn(@PathVariable String msg) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.getFuture().handle((t, u) -> {
            if (u != null) {
                return u;
            }
            if (t.isAck()) {
//                成功发送到交换机
            } else {
//                发送到交换机失败
            }

            return null;
        });
        rabbitTemplate.sendAndReceive(RabbitMqConstant.EXCHANGE, "routingKey", new Message(msg.getBytes()), correlationData);
        return ResponseDto.success(msg);
    }


}
