package com.zzh.rabbit;

import com.zzh.constant.RabbitMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/11/25 14:21
 */
@Slf4j
@Configuration
public class RabbitConsumer {

    @RabbitListener(queues = RabbitMqConstant.QUEUE)
    public void queueConsumer(String message) {
        log.info("队列={},消费一条消息 msg={}", RabbitMqConstant.QUEUE, message);
    }

    @RabbitListener(queues = RabbitMqConstant.FANOUT_QUEUE)
    public void fanoutConsumer(String message) {
        log.info("队列={},消费一条消息 msg={}", RabbitMqConstant.FANOUT_QUEUE, message);
    }


    @RabbitListener(queues = RabbitMqConstant.FANOUT_QUEUE2)
    public void fanoutConsumer2(String message) {
        log.info("队列 ={},消费一条消息 msg={}", RabbitMqConstant.FANOUT_QUEUE2, message);
    }


    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = RabbitMqConstant.QUEUE, durable = "true"), exchange = @Exchange(value = RabbitMqConstant.EXCHANGE, ignoreDeclarationExceptions = "true")))
    public void listen(String msg) {
        log.info("消费一条消息 {}", msg);
    }

    //    使用注解的方式，声明队列，交换器和绑定关系
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = RabbitMqConstant.DIRECT_QUEUE), exchange = @Exchange(value = RabbitMqConstant.DIRECT_EXCHANGE, type = ExchangeTypes.DIRECT), key = {"key1", "zzh"})})
    public void directQueueConsumer1(String msg) {
        log.info("队列 ={},消费一条消息 msg={}", RabbitMqConstant.DIRECT_QUEUE, msg);
    }

    //    topic exchange 和 direct exchange 的区别：
//    topic 的 binding key 可以执行通配符类型的  * : 代表一个单词    # ：代表0个或多个
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = RabbitMqConstant.DIRECT_QUEUE2), exchange = @Exchange(value = RabbitMqConstant.DIRECT_EXCHANGE, type = ExchangeTypes.DIRECT), key = {"key2", "zzh"})})
    public void directQueueConsumer2(String msg) {
        log.info("队列 ={},消费一条消息 msg={}", RabbitMqConstant.DIRECT_QUEUE2, msg);
    }


    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = RabbitMqConstant.TOPIC_QUEUE), exchange = @Exchange(value = RabbitMqConstant.TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC), key = {"zzh.#"})})
    public void topicQueueConsumer1(String msg) {
        log.info("队列 ={},消费一条消息 msg={}", RabbitMqConstant.TOPIC_QUEUE, msg);
    }

    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = RabbitMqConstant.TOPIC_QUEUE2), exchange = @Exchange(value = RabbitMqConstant.TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC), key = {"#"})})
    public void topicQueueConsumer2(String msg) {
        log.info("队列 ={},消费一条消息 msg={}", RabbitMqConstant.TOPIC_QUEUE2, msg);
    }

}
