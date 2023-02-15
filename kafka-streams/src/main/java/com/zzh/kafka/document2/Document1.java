package com.zzh.kafka.document2;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.errors.StreamsUncaughtExceptionHandler;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.internals.KeyValueStoreBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: kafkaStreams异常处理
 * @Author: zzh
 * @Crete 2023/2/13 11:07
 */
public class Document1 {
    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> logger.setLevel(Level.INFO));
    }

    /**
     * kafkaStreams异常处理：
     *
     *
     */
    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafkaStreamsExceptionHandler");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(StreamsConfig.TOPIC_PREFIX, "test");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder streamsBuilder = new StreamsBuilder();

//        StoreBuilder storeBuilder=new KeyValueStoreBuilder(, , , )
//        streamsBuilder.addStateStore()

        KTable<String, String> fromTable = streamsBuilder.table("test", Materialized.with(Serdes.String(),Serdes.String()));

        GlobalKTable<String, String> fromGlobalTable = streamsBuilder.globalTable("test", Consumed.with(Serdes.String(), Serdes.String()));

        KStream<String, String> fromStream = streamsBuilder.stream("test", Consumed.with(Serdes.String(), Serdes.String()));




        try (KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), properties)) {

            //异常处理
            kafkaStreams.setUncaughtExceptionHandler(exception -> {
                System.out.println("发生异常，e=" + exception);
                return StreamsUncaughtExceptionHandler.StreamThreadExceptionResponse.SHUTDOWN_CLIENT;
            });
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
