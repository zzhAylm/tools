package com.zzh.kafka.streams;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.zzh.kafka.streams.module.Transaction;
import com.zzh.kafka.streams.module.TransactionReward;
import com.zzh.kafka.streams.serders.JsonSerdes;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: stateful ,transformValues repartition
 * @Author: zzh
 * @Crete 2022/11/23 22:10
 */
@Slf4j
public class KafkaStreamDemo3 {
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


    private static final String APPLICATION_ID = "stateful-transform-values-process";
    private static final String SOURCE_TOPIC = "input-json";
    private static final String OUTPUT_TOPIC = "output-json";
    private static final String BOOTSTRAP_SERVER = "127.0.0.1:9092";
    private static final String SATE_STORE_NAME = "state_store2";

    public static void main(String[] args) {

        Properties properties = new Properties();

        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_ID);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        properties.put(StreamsConfig.STATE_DIR_CONFIG, "/Users/zzh/Company/tools/kafka_2.12-3.3.1/data");
        properties.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 3);

        StreamsBuilder streamsBuilder = new StreamsBuilder();

        StoreBuilder<KeyValueStore<String, Integer>> storeBuilder = Stores.keyValueStoreBuilder(Stores.persistentKeyValueStore(SATE_STORE_NAME), Serdes.String(), Serdes.Integer());

        streamsBuilder.addStateStore(storeBuilder);

        KStream<String, Transaction> ks0 = streamsBuilder.stream(SOURCE_TOPIC, Consumed.with(Serdes.String(), JsonSerdes.transactionSerde()).withName("input-process")).peek((key, value) -> log.info("peek1,key-{},value={}", key, value), Named.as("peek1-process"));

        ks0.mapValues((TransactionReward::newBuilder))
                .selectKey((k, v) -> v.getCustomerId())
                .repartition(Repartitioned.with(Serdes.String(), JsonSerdes.transactionRewardSerde()))
                .transformValues(() -> new ValueTransformer<TransactionReward, TransactionReward>() {

                    private KeyValueStore<String, Integer> keyValueStore;

                    @Override
                    public void init(ProcessorContext context) {
                        this.keyValueStore = context.getStateStore(SATE_STORE_NAME);
                    }

                    @Override
                    public TransactionReward transform(TransactionReward data) {

                        String customerId = data.getCustomerId();

                        Integer count = keyValueStore.get(customerId);
                        if (count == null || count == 0) {
                            count = data.getRewardPoints();
                        } else {
                            count += data.getRewardPoints();
                        }
                        keyValueStore.put(customerId, count);
                        return TransactionReward.builder().totalPoints(count).build();
                    }

                    @Override
                    public void close() {

                    }
                }, Named.as("transform-value-processor"), SATE_STORE_NAME).to(OUTPUT_TOPIC, Produced.with(Serdes.String(), JsonSerdes.transactionRewardSerde()).withName("output-processor"));

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
