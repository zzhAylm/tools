package com.zzh.kafka.streams;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.apache.tomcat.jni.User;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * @Description: stateful ,inner join operation join()
 * @Author: zzh
 * @Crete 2022/11/23 22:10
 */
@Slf4j
public class KafkaStreamDemo4 {
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


    private static final String APPLICATION_ID = "stateful-process";
    private static final String USER_TOPIC = "user-info";
    private static final String ADDRESS_TOPIC = "address-info";
    private static final String BOOTSTRAP_SERVER = "127.0.0.1:9092";

    public static void main(String[] args) {

        Properties properties = new Properties();

        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_ID);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        properties.put(StreamsConfig.STATE_DIR_CONFIG, "/Users/zzh/Company/tools/kafka_2.12-3.3.1/data");
        properties.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 3);

        StreamsBuilder streamsBuilder = new StreamsBuilder();


        KStream<String, String> ks0 = streamsBuilder.stream(USER_TOPIC, Consumed.with(Serdes.String(), Serdes.String()).withName("user-stream"));
        KStream<String, String> ks1 = streamsBuilder.stream(ADDRESS_TOPIC, Consumed.with(Serdes.String(), Serdes.String()).withName("address-stream"));

        KStream<String, String> userKs = ks0.map((k, v) -> KeyValue.pair(v.split(",")[0], v),Named.as("user-map-processor"));
        KStream<String, String> addressKs = ks1.map((k, v) -> KeyValue.pair(v.split(",")[0], v),Named.as("address-map-processor"));


        userKs.join(addressKs, (left,right)->left+"----"+right,JoinWindows.of(Duration.ofMinutes(1)),StreamJoined.with(Serdes.String(), Serdes.String(), Serdes.String()))
                .print(Printed.<String, String>toSysOut().withLabel("inner-join-print"));



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
