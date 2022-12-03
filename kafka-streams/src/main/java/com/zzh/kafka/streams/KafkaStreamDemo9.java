package com.zzh.kafka.streams;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.zzh.kafka.streams.module.SalesStatus;
import com.zzh.kafka.streams.serders.JsonSerdes;
import com.zzh.kafka.streams.statestore.StateStoreQueryServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: stateful , groupByKey() aggregate() state-store() query
 * @Author: zzh
 * @Crete 2022/11/23 22:10
 */
@Slf4j
public class KafkaStreamDemo9 {
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


    private static final String APPLICATION_ID = "stateful-aggregate-state-store-query-processor";
    private static final String INPUT_TOPIC = "sales-state-store-query";
    private static final String BOOTSTRAP_SERVER = "127.0.0.1:9092";

    public static void main(String[] args) {

        Properties properties = new Properties();

        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_ID);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        properties.put(StreamsConfig.STATE_DIR_CONFIG, "/Users/zzh/Company/tools/kafka_2.12-3.3.1/data/aggregate-state-query");
        properties.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 1);
        properties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);

        StreamsBuilder streamsBuilder = new StreamsBuilder();


        streamsBuilder.stream(INPUT_TOPIC, Consumed.with(Serdes.String(), JsonSerdes.salesSerde()).withName("input-word-stream"))

                .groupBy((k, v) -> v.getDepartment(), Grouped.with(Serdes.String(), JsonSerdes.salesSerde()))
                .aggregate(SalesStatus::new, (k, v, aggregate) -> {
                            if (aggregate.getDepartment() == null) {
                                aggregate.setDepartment(v.getDepartment());
                                aggregate.setCount(1);
                                aggregate.setAverageAmount(v.getSalesAmount());
                                aggregate.setTotalAmount(v.getSalesAmount());
                            } else {
                                aggregate.setCount(aggregate.getCount() + 1);
                                aggregate.setTotalAmount(aggregate.getTotalAmount() + v.getSalesAmount());
                                aggregate.setAverageAmount(aggregate.getTotalAmount() / aggregate.getCount());
                            }
                            return aggregate;
                        }, Named.as("aggregate-processor"),
                        Materialized.<String, SalesStatus, KeyValueStore<Bytes, byte[]>>as("aggregate-state-store-query").withKeySerde(Serdes.String()).withValueSerde(JsonSerdes.salesStatusSerde()))
                .toStream()
                .print(Printed.<String, SalesStatus>toSysOut().withLabel("aggregate-print"));


        Topology topology = streamsBuilder.build();

        final KafkaStreams kafkaStreams = new KafkaStreams(topology, properties);
        //启动查询
        new StateStoreQueryServer(kafkaStreams, "aggregate-state-store-query").start();
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
