package com.zzh.config;

import com.zzh.constant.RabbitMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
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

}
