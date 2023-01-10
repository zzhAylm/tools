package com.zzh.kafka.document;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: .suppress(Suppressed.untilWindowCloses ( unbounded ()))
 * @Author: zzh
 * @Crete 2023/1/10 11:47
 */
public class KafkaStreams6 {


    public static void main(String[] args) {
        Properties properties = new Properties();


        StreamsBuilder streamsBuilder = new StreamsBuilder();









        //kafkaStreams 关闭程序
        try (final KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), properties)) {
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
