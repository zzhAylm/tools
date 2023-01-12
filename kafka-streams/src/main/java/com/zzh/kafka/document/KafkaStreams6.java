package com.zzh.kafka.document;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.processor.PunctuationType;
import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;

import java.time.Duration;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: .suppress(Suppressed.untilWindowCloses ( unbounded ()))
 * @Author: zzh
 * @Crete 2023/1/10 11:47
 */
public class KafkaStreams6 {


    //分区数=task数， 一个server包含多个实例，一个实例包含多个线程，一个线程可以包含多个task，可以通过增加实例数或线程数增加并发度
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "suppress-application");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());


        StreamsBuilder streamsBuilder = new StreamsBuilder();


        //kafkaStreams 关闭程序
        Topology topology = streamsBuilder.build();
//        topology.addSource()
//        topology.addProcessor(, )
//        topology.addSink(, , )
        try (final KafkaStreams streams = new KafkaStreams(topology, properties)) {
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

    static class WordCountProcessor implements Processor<String, String, String, String> {

        private KeyValueStore<String, Integer> kvStore;

        @Override
        public void init(final ProcessorContext<String, String> context) {
            context.schedule(Duration.ofSeconds(1), PunctuationType.STREAM_TIME, timestamp -> {
                try (final KeyValueIterator<String, Integer> iter = kvStore.all()) {
                    while (iter.hasNext()) {
                        final KeyValue<String, Integer> entry = iter.next();
                        context.forward(new Record<>(entry.key, entry.value.toString(), timestamp));
                    }
                }
            });
            kvStore = context.getStateStore("Counts");
        }

        @Override
        public void process(final Record<String, String> record) {
            final String[] words = record.value().toLowerCase(Locale.getDefault()).split("\\W+");

            for (final String word : words) {
                final Integer oldValue = kvStore.get(word);

                if (oldValue == null) {
                    kvStore.put(word, 1);
                } else {
                    kvStore.put(word, oldValue + 1);
                }
            }
        }

        @Override
        public void close() {
            // close any resources managed by this processor
            // Note: Do not close any StateStores as these are managed by the library
        }
    }
}
