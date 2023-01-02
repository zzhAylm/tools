package com.zzh.kafka.document;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/30 17:26
 */
public class KafkaStreams2 {
    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> logger.setLevel(Level.INFO));
    }

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafkaStream2");
        props.put(StreamsConfig.TOPIC_PREFIX, "test");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder streamsBuilder = new StreamsBuilder();

        streamsBuilder.stream("test", Consumed.with(Serdes.String(), Serdes.String()).withName("read-test"))
                .peek((key, value) -> System.out.println("读取数据：key=" + key + "----value=" + value))
                .flatMap((key, value) -> Arrays.stream(value.split("\\w+")).map(v -> KeyValue.pair(v, 1L)).collect(Collectors.toList()))
                .groupByKey(Grouped.with(Serdes.String(), Serdes.Long()))
                .count(Named.as("count"), Materialized.with(Serdes.String(), Serdes.Long()))
                .toStream()
                .print(Printed.<String, Long>toSysOut().withLabel("print"));

        // kafkaStream的关闭和开启
        try (KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), props)) {
            kafkaStreams.start();
            //当程序停止的时候，关闭kafkaStreams，否则等待
            CountDownLatch countDownLatch = new CountDownLatch(1);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                kafkaStreams.close();
                countDownLatch.countDown();
            }));
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
