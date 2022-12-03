package com.zzh.kafka.streams;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Named;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: stateless ,无状态的处理过程
 * @Author: zzh
 * @Crete 2022/11/23 22:10
 */
@Slf4j
public class KafkaStreamDemo1 {
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

    private static final String APPLICATION_ID = "steam-process";
    private static final String SOURCE_TOPIC = "input";
    private static final String OUTPUT_TOPIC = "output";
    private static final String BOOTSTRAP_SERVER = "127.0.0.1:9092";

    public static void main(String[] args) {

        Properties properties = new Properties();

        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_ID);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);

        StreamsBuilder streamsBuilder = new StreamsBuilder();

        streamsBuilder.stream(SOURCE_TOPIC, Consumed.with(Serdes.String(), Serdes.String()).withName("input-process"))
                .peek((key, value) -> log.info("peek1,key-{},value={}", key, value), Named.as("peek1-process"))
                .mapValues(value -> value.toUpperCase(), Named.as("map-process"))
                .filter((k, v) -> v.length() > 5, Named.as("filter-process"))
                .peek((key, value) -> log.info("peek2,key={},v={}", key, value), Named.as("peek2-process"))
                .to(OUTPUT_TOPIC, Produced.with(Serdes.String(), Serdes.String()).withName("output-process"));

        final KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), properties);

        CountDownLatch countDownLatch = new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            kafkaStreams.close();
            countDownLatch.countDown();
            log.info("kafka stream close！");
        }));

        kafkaStreams.start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.info("kafka stream 关闭失败", e);
            throw new RuntimeException(e);
        }
    }
}
