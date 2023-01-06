package com.zzh.kafka.document;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/4 14:00
 */
@Slf4j
public class KafkaStreams5 {
    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> logger.setLevel(Level.INFO));
    }

    //join
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-join-operation");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        StreamsBuilder builder = new StreamsBuilder();


        KStream<String, String> stream1 = builder.stream("test", Consumed.with(Serdes.String(), Serdes.String()))
                .map(((key, value) -> KeyValue.pair(value, value)))
                .peek(((key, value) -> log.info("key={},value={}", key, value)));


        KStream<String, String> stream2 = builder.stream("test2", Consumed.with(Serdes.String(), Serdes.String()))
                .map(((key, value) -> KeyValue.pair(value, value)))
                .peek(((key, value) -> log.info("key={},value={}", key, value)));


        //内连接
        stream1.join(
                stream2,
                (v1, v2) -> v1 + "---------" + v2,
                JoinWindows.ofTimeDifferenceAndGrace(Duration.ofMinutes(1), Duration.ofMinutes(0))
        ).print(Printed.<String, String>toSysOut().withLabel("print"));

        //左连接
        stream1.leftJoin(
                stream2,
                (v1, v2) -> v1 + "---------" + v2,
                JoinWindows.ofTimeDifferenceAndGrace(Duration.ofMinutes(1), Duration.ofMinutes(0))
        ).print(Printed.<String, String>toSysOut().withLabel("print"));

        //外连接
        stream1.outerJoin(
                stream2,
                (v1, v2) -> v1 + "---------" + v2,
                JoinWindows.ofTimeDifferenceAndGrace(Duration.ofMinutes(1), Duration.ofMinutes(0))
        ).print(Printed.<String, String>toSysOut().withLabel("print"));

        //kafkaStreams 关闭程序
        try (final KafkaStreams streams = new KafkaStreams(builder.build(), props)) {
            final CountDownLatch latch = new CountDownLatch(1);
            // attach shutdown handler to catch control-c
            Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
                @Override
                public void run() {
                    streams.close();
                    latch.countDown();
                }
            });
            try {
                streams.start();
                latch.await();
            } catch (Throwable e) {
                System.exit(1);
            }
            System.exit(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
