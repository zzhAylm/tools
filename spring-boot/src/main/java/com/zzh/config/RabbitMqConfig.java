package com.zzh.config;

import com.zzh.constant.RabbitMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/11/24 12:32
 */
@Slf4j
@Configuration
public class RabbitMqConfig {


    @Bean
    public FanoutExchange exchange() {
        return new FanoutExchange(RabbitMqConstant.FANOUT_EXCHANGE);
    }

    @Bean
    public Queue queue() {
        return new Queue(RabbitMqConstant.FANOUT_QUEUE);
    }
    @Bean
    public Queue queue2() {
        return new Queue(RabbitMqConstant.FANOUT_QUEUE2);
    }
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange());
    }
    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queue2()).to(exchange());
    }
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    public RabbitMqConfig(RabbitTemplate template) {
        template.setReturnsCallback(returned -> {
//            消息 从交换机到队列失败
            log.info("return is {}",returned);
        });
    }
//    持久化
    @Bean
    public DirectExchange exchangeDurable(){
        return new DirectExchange("zzh.durable.exchange", true, false);
    }

    @Bean
    public Queue queueDurable(){
        return QueueBuilder.durable("zzh.durable.queue").build();
    }

}
