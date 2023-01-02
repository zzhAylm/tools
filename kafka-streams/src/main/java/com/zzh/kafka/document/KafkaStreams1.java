package com.zzh.kafka.document;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: kafkaStreams
 * @Author: zzh
 * @Crete 2022/12/30 14:08
 */
public class KafkaStreams1 {
    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> logger.setLevel(Level.INFO));
    }

    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "KafkaStream1");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.TOPIC_PREFIX, "test");
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder streamsBuilder = new StreamsBuilder();

        streamsBuilder.stream("test", Consumed.with(Serdes.String(), Serdes.String()).withName("read-test-topic"))
                .peek(((key, value) -> System.out.println("key=" + key + "------value=" + value)))
                .selectKey(((key, value) -> value))
                .filter((key, value) -> value.length() > 5)
                .map((key, value) -> KeyValue.pair(key, value.toLowerCase()))
                .groupBy((key, value) -> key)
                .count(Materialized.as("count"))
                .toStream()
                .print(Printed.<String, Long>toSysOut().withLabel("print"));


        //开启加，关闭
        try (KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), props);) {
            kafkaStreams.start();
            //当程序停止的时候，关闭kafkaStreams，否则等待
            CountDownLatch countDownLatch = new CountDownLatch(1);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                kafkaStreams.close();
                countDownLatch.countDown();
            }));
            countDownLatch.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
