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
 * @Description: group()   windows operation() Tumbling time windows suppress:挡住，hopping 移动的窗口，过去一分钟内的窗口数据（一分钟前到现在的一个窗口数据）
 * <p>
 * tumbling window 统计没分钟的数据，如， 第一分钟，第二分钟
 * hopping window 统计的是过去一分钟的数据 advanceBy(),在tumbling window的基础上加一个跳动频率，就是hopping window
 * @Author: zzh
 * @Crete 2022/11/23 22:10
 */
@Slf4j
public class KafkaStreamDemo14 {
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
    private static final String APPLICATION_ID = "hopping-window-processor";
    private static final String INPUT_TOPIC = "hopping-window";
    private static final String BOOTSTRAP_SERVER = "127.0.0.1:9092";

    public static void main(String[] args) {

        Properties properties = new Properties();

        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_ID);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        properties.put(StreamsConfig.STATE_DIR_CONFIG, "/Users/zzh/Company/tools/kafka_2.12-3.3.1/data/window-operation");
        properties.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 3);
        properties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);

        StreamsBuilder streamsBuilder = new StreamsBuilder();

        streamsBuilder.stream(INPUT_TOPIC, Consumed.with(Serdes.String(), JsonSerdes.netTrafficSerde()).withName("hopping-window-processor"))
                .groupBy((k, v) -> v.getPage(), Grouped.with(Serdes.String(), JsonSerdes.netTrafficSerde()))
                .windowedBy(TimeWindows.ofSizeAndGrace(Duration.ofMinutes(1), Duration.ofSeconds(0)).advanceBy(Duration.ofSeconds(10)))
                .count(Named.as("hopping-window-count"), Materialized.as("hopping-window-state-store"))
                .suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded()))
                .toStream()
                .foreach(((key, value) -> log.info("[{}---{}] page={},count={}", key.window().start(), key.window().end(), key.key(), value)));

        Topology topology = streamsBuilder.build();
        final KafkaStreams kafkaStreams = new KafkaStreams(topology, properties);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            kafkaStreams.close();
            countDownLatch.countDown();
            log.info("kafka stream close！");
        }));
        try {
            kafkaStreams.start();
            countDownLatch.await();
        } catch (Throwable e) {
            System.exit(1);
        }
        System.exit(0);
    }
}
