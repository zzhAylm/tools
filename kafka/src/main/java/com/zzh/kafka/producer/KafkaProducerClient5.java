package com.zzh.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @Description: 数据可靠性 ，ack 应答，当ack=1，ack=0，ack=-1  ， 分区的 leader 和副本flower
 * @Author: zzh
 * @Crete 2022/12/10 00:47
 */
public class KafkaProducerClient5 {

    private static final String TOPIC_NAME = "test";

    public static void main(String[] args) {

        Properties properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, MyPartitioner.class.getName());

        //buff size:仓库的大小
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 32 * 1024);

        //一次最多装在的大小，16k，
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        // 一次发车的最大间隔 ：2ms
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 2);

        //ack 应答机制设置
        properties.put(ProducerConfig.ACKS_CONFIG, "1");

        //重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);

        try (KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties)) {
            kafkaProducer.send(new ProducerRecord<>(TOPIC_NAME, "hello word！"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
