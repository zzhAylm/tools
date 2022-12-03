package com.zzh.kafka.streams;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.zzh.kafka.streams.module.SalesStatus;
import com.zzh.kafka.streams.serders.JsonSerdes;
import com.zzh.kafka.streams.statestore.StateStoreQueryServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/11/23 22:10
 * <p>
 * kafka的生产这配置 message.timestamp.type: CreatedTime 或 LogAppendTime
 * <p>
 * topic的配置：CreatedTime
 * 消费者收到消息的时间就是 CreatedTime
 * <p>
 * topic的配置：LogAppendTime
 * 消费者收到消息的时间就是 本地的时间戳，覆盖原来的CreatedTime，会略大于CreatedTime
 */
@Slf4j
public class KafkaStreamDemo10 {
    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> logger.setLevel(Level.INFO));
    }
    //kafka stream 实时处理。流处理
    //stateless
    //stateful
    //keyTable
    //keyStream
    //globalKeyTable
    //spack，streaming，

    private static final String INPUT_TOPIC = "time-default";
    private static final String BOOTSTRAP_SERVER = "127.0.0.1:9092";

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);

        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());


        KafkaProducer<String,String> producer= new KafkaProducer<>(properties);

        long timeMillis = System.currentTimeMillis();

        log.info("time"+timeMillis);
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(INPUT_TOPIC, null, timeMillis, null, "the data");

        Future<RecordMetadata> future = producer.send(producerRecord, (metadata, exception) -> {
            if (exception == null) {
                log.info("the record is send:{}", metadata);
            }
        });
        producer.flush();
        future.get();
        producer.close();
    }
}
