package com.zzh.kafka.streams;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.zzh.kafka.streams.serders.JsonSerdes;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * @Description: group()   windows operation() Tumbling time windows suppress:挡住，只统计最后结果
 * @Author: zzh
 * @Crete 2022/11/23 22:10
 */
@Slf4j
public class KafkaStreamDemo13 {
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
    //有 group操作的时候，缓存一段时间，要么到达缓存的大小，要么缓存时间到，他才会往下游发送流
    private static final String APPLICATION_ID = "window-suppress-buff-processor";
    private static final String INPUT_TOPIC = "suppress-buff";
    private static final String BOOTSTRAP_SERVER = "127.0.0.1:9092";

    public static void main(String[] args) {

        Properties properties = new Properties();

        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_ID);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        properties.put(StreamsConfig.STATE_DIR_CONFIG, "/Users/zzh/Company/tools/kafka_2.12-3.3.1/data/window-operation");
        properties.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 3);
        properties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);

        StreamsBuilder streamsBuilder = new StreamsBuilder();


        streamsBuilder.stream(INPUT_TOPIC, Consumed.with(Serdes.String(), Serdes.String()).withName("suppress-buff-stream"))


                .flatMap((k,v)-> Arrays.stream(v.split("\\s")).map(e-> KeyValue.pair(e, 1)).collect(Collectors.toList()))
                .groupByKey(Grouped.with(Serdes.String(),Serdes.Integer()))
                .windowedBy(TimeWindows.ofSizeAndGrace(Duration.ofSeconds(30),Duration.ofSeconds(0)))
                .count(Named.as("suppress-count"), Materialized.as("suppress-state-store"))
                .suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded()))//挡住，不展示中间过程，只展示一分钟的最后的统计结果
                .toStream()
                .foreach(((key, value) -> log.info("[{}--{}] for word:{},count:{}",key.window().start(),key.window().end(),key.key(),value)));

        Topology topology = streamsBuilder.build();

        final KafkaStreams kafkaStreams = new KafkaStreams(topology, properties);

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
