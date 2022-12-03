package com.zzh.kafka.streams.serders;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.zzh.kafka.streams.module.Sales;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Printed;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: stateful , groupByKey() reduce() ，groupBy()
 * @Author: zzh
 * @Crete 2022/11/23 22:10
 */
@Slf4j
public class KafkaStreamDemo8 {
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
    private static final String INPUT_TOPIC = "input-sales1";
    private static final String BOOTSTRAP_SERVER = "127.0.0.1:9092";

    public static void main(String[] args) {

        Properties properties = new Properties();

        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_ID);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        properties.put(StreamsConfig.STATE_DIR_CONFIG, "/Users/zzh/Company/tools/kafka_2.12-3.3.1/data/state1");
        properties.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 3);
        properties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);

        StreamsBuilder streamsBuilder = new StreamsBuilder();


        streamsBuilder.stream(INPUT_TOPIC, Consumed.with(Serdes.String(), JsonSerdes.salesSerde()).withName("input-word-stream"))
                .mapValues((value ->{value.setTotalSalesAmount(value.getSalesAmount());return value;}))
                .groupBy((k, v) -> v.getUserName(), Grouped.with(Serdes.String(), JsonSerdes.salesSerde()))
                .reduce(((value1, value2) -> Sales.builder().totalSalesAmount(value1.getTotalSalesAmount() + value2.getSalesAmount()).department(value2.getDepartment()).userName(value2.getUserName()).salesAmount(value2.getSalesAmount()).build()),Materialized.as("sales-by-name"))
                .toStream()
                .groupBy((k, v) -> v.getDepartment(), Grouped.with(Serdes.String(), JsonSerdes.salesSerde()))
                .reduce(((value1, value2) -> value2.getTotalSalesAmount() > value1.getTotalSalesAmount() ? value2 : value1),Materialized.as("sales-by-dept"))
                .toStream()
                .print(Printed.<String, Sales>toSysOut().withLabel("print"));


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
