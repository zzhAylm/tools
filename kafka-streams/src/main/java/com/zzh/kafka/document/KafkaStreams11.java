package com.zzh.kafka.document;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyWindowStore;
import org.apache.kafka.streams.state.WindowStoreIterator;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import static org.apache.kafka.streams.kstream.Suppressed.BufferConfig.unbounded;

/**
 * @Description: 查询本地窗口商店
 * @Author: zzh
 * @Crete 2023/1/13 14:21
 * <p>
 * <p>
 * 窗口类型：
 * tumbling window 统计没分钟的数据，如， 第一分钟，第二分钟，一分钟只会展示一次结果
 * <p>
 * hopping window 统计的是过去一分钟的数据 advanceBy(),在tumbling window的基础上加一个跳动频率，就是hopping window，：每十秒展示一次结果，统计一分钟内的数据
 * <p>
 * sliding window ： 只有当数据变化时，才会形成新的窗口，如数据退出窗口，或者新的数据加入窗口,窗口的大小是60秒，两个数据之间的时间戳超过窗口，就会创建新的窗口
 */
public class KafkaStreams11 {
    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> logger.setLevel(Level.INFO));
    }


    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "key-value-window-store-application");
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
        groupedByWord.windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofSeconds(60)))
//        groupedByWord.windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofSeconds(60)).advanceBy(Duration.ofSeconds(10)))
//        groupedByWord.windowedBy(SlidingWindows.ofTimeDifferenceWithNoGrace(Duration.ofSeconds(60)))
                .count(Named.as("count"), Materialized.with(Serdes.String(), Serdes.Long()))
                .suppress(Suppressed.untilWindowCloses(unbounded()))
                .toStream().foreach((key, value) -> System.out.println("key=" + key.key() + "\tvalue=" + value));


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

    //窗口类型查询
    public void queryWindow(KafkaStreams kafkaStreams){
        // Get the window store named "CountsWindowStore"
        ReadOnlyWindowStore<String, Long> windowStore =
                kafkaStreams.store(StoreQueryParameters.fromNameAndType("CountsWindowStore", QueryableStoreTypes.windowStore()));

// Fetch values for the key "world" for all of the windows available in this application instance.
// To get *all* available windows we fetch windows from the beginning of time until now.
        Instant timeFrom = Instant.ofEpochMilli(0); // beginning of time = oldest available
        Instant timeTo = Instant.now(); // now (in processing-time)
        WindowStoreIterator<Long> iterator = windowStore.fetch("world", timeFrom, timeTo);
        while (iterator.hasNext()) {
            KeyValue<Long, Long> next = iterator.next();
            long windowTimestamp = next.key;
            System.out.println("Count of 'world' @ time " + windowTimestamp + " is " + next.value);
        }

        //
    }
}
