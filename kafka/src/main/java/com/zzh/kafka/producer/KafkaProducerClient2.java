package com.zzh.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @Description: command+p:现实方法的所有参数, callback 回调函数
 * @Author: zzh
 * @Crete 2022/12/10 00:47
 */
@Slf4j
public class KafkaProducerClient2 {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerClient2.class);

    private static final String TOPIC_NAME = "test";

    public static void main(String[] args) {

        Properties properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        try (KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties)) {
            kafkaProducer.send(new ProducerRecord<>(TOPIC_NAME, "hello word！"),(metadata, exception) -> {
                if (exception==null){
                    logger.info("返回数据：topic:{},partition:{}",metadata.topic(),metadata.partition());
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
