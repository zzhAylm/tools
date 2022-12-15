package com.zzh.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 *
 * @Author: zzh
 * @Crete 2022/12/10 00:47
 */
public class KafkaProducerClient7 {

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
        properties.put(ProducerConfig.ACKS_CONFIG, "-1");

        //重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);
        //使用事物，必须手动指定事物id
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "01");

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        try {
            //初始化事物
            kafkaProducer.initTransactions();
            //开启事物
            kafkaProducer.beginTransaction();

            kafkaProducer.send(new ProducerRecord<>(TOPIC_NAME, "hello word！"));
            //提交事物
            kafkaProducer.commitTransaction();
        } catch (Exception e) {
            //会滚事物
            kafkaProducer.abortTransaction();
            throw new RuntimeException(e);
        } finally {
            kafkaProducer.close();
        }


    }
}
