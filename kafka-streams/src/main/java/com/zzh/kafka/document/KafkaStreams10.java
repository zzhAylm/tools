package com.zzh.kafka.document;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 查询本地键值对存储与查询
 * @Author: zzh
 * @Crete 2023/1/13 13:42
 */
public class KafkaStreams10 {
    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> logger.setLevel(Level.INFO));
    }

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "key-value-store-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.STATE_DIR_CONFIG, "/Users/zzh/Company/tools/kafka_2.12-3.3.1/data");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder streamBuilder = new StreamsBuilder();

        KStream<String, String> textLines = streamBuilder.stream("input-topic", Consumed.with(Serdes.String(), Serdes.String()));

        // Define the processing topology (here: WordCount)
        KGroupedStream<String, String> groupedByWord = textLines
                .peek((key, value) -> System.out.println("key=" + key + "\tvalue=" + value))
                .flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\W+")))
                .groupBy((key, word) -> word, Grouped.with(Serdes.String(), Serdes.String()));

        // Create a key-value store named "CountsKeyValueStore" for the all-time word counts
        groupedByWord.count(Named.as("count"), Materialized.as("CountsKeyValueStore"))
                .toStream().foreach((key, value) -> System.out.println("key=" + key + "\tvalue=" + value));


        // Start an instance of the topology
        //开启加，关闭
        try (KafkaStreams kafkaStreams = new KafkaStreams(streamBuilder.build(), props);) {
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


    public static void query(KafkaStreams kafkaStreams) {
        ReadOnlyKeyValueStore<String, Long> keyValueStore = kafkaStreams.store(StoreQueryParameters.fromNameAndType("CountsKeyValueStore", QueryableStoreTypes.keyValueStore()));
// Get value by key
        System.out.println("count for zzh:" + keyValueStore.get("zzh"));

// Get the values for a range of keys available in this application instance
//        KeyValueIterator<String, Long> range = keyValueStore.range("all", "streams");
//        while (range.hasNext()) {
//            KeyValue<String, Long> next = range.next();
//            System.out.println("count for " + next.key + ": " + next.value);
//        }

// Get the values for all of the keys available in this application instance
        KeyValueIterator<String, Long> range = keyValueStore.all();
        while (range.hasNext()) {
            KeyValue<String, Long> next = range.next();
            System.out.println("count for " + next.key + ": " + next.value);
        }

    }
}
