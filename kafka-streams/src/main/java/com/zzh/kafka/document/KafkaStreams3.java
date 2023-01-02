package com.zzh.kafka.document;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/30 19:48
 */
public class KafkaStreams3 {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-pipe");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();

        builder.stream("streams-plaintext-input").to("streams-pipe-output");


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
