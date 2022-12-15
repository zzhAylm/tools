package com.zzh.kafka.consumer;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.zzh.kafka.producer.KafkaProducerClient2;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

/**
 * @Description: 订阅某个topic
 * @Author: zzh
 * @Crete 2022/12/14 14:21
 */
@Slf4j
public class ConsumerClient1 {
    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<ch.qos.logback.classic.Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> logger.setLevel(Level.INFO));
    }
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerClient2.class);

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //消费者组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer_group_1");

        try (KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);) {
            HashSet<String> topics = new HashSet<>();
            topics.add("test");
            kafkaConsumer.subscribe(topics);
            while (true) {
                ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));
                consumerRecords.forEach(consumerRecord -> logger.info("消费的数据：" + consumerRecord));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
