package com.zzh.kafka.streams;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.zzh.kafka.streams.serders.JsonSerdes;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: group()   windows operation() Tumbling time windows suppress:挡住，hopping 移动的窗口，过去一分钟内的窗口数据
 * session window: 可扩展的window
 * <p>
 * tumbling window 统计没分钟的数据，如， 第一分钟，第二分钟
 * hopping window 统计的是过去一分钟的数据
 * sliding window : 只有点那个有数据进来的时候才会统计
 * session window : 可扩展的窗口，直到两次出现时间超过一个窗口，否则会出现在同一个窗口中
 * 一个用户在 一个session周期内 若果多次访问次数会叠加，（多次访问的间隔不得超过设置的时间范围），两次访问的时间范围超过设置的间隔时间，这个用户的访问次数会从0重新开始
 * 维持一个活跃的窗口，可以无限叠加的窗口数据
 * @Author: zzh
 * @Crete 2022/11/23 22:10
 */
@Slf4j
public class KafkaStreamDemo16 {
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
    private static final String APPLICATION_ID = "session-window-processor";
    private static final String INPUT_TOPIC = "session-window";
    private static final String BOOTSTRAP_SERVER = "127.0.0.1:9092";

    public static void main(String[] args) {

        Properties properties = new Properties();

        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_ID);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        properties.put(StreamsConfig.STATE_DIR_CONFIG, "/Users/zzh/Company/tools/kafka_2.12-3.3.1/data/window-operation");
        properties.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 3);
        properties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);

        StreamsBuilder streamsBuilder = new StreamsBuilder();

        streamsBuilder.stream(INPUT_TOPIC, Consumed.with(Serdes.String(), JsonSerdes.netTrafficSerde()).withName("sliding-window-processor"))
                .groupBy((k, v) -> v.getRemoteAddress(), Grouped.with(Serdes.String(), JsonSerdes.netTrafficSerde()))
                .windowedBy(SessionWindows.ofInactivityGapWithNoGrace(Duration.ofSeconds(30)))
                .count(Named.as("session-window-count"), Materialized.as("session-window-state-store"))
                .suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded()))
                .toStream()
                .foreach(((key, value) -> log.info("[{}---{}] user={},count={}", key.window().start(), key.window().end(), key.key(), value)));

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
