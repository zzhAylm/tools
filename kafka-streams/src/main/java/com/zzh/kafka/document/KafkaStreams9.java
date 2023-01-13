package com.zzh.kafka.document;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.processor.MockProcessorContext;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.Stores;

import java.util.Properties;

/**
 * @Description: 单元测试
 * @Author: zzh
 * @Crete 2023/1/13 11:33
 */
public class KafkaStreams9 {
    //
    public static void main(String[] args) {
        final Properties props = new Properties();
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Long().getClass());
        props.put("some.other.config", "some config value");
        final MockProcessorContext context = new MockProcessorContext(props);


        final KeyValueStore<String, Integer> store =
                Stores.keyValueStoreBuilder(
                                Stores.inMemoryKeyValueStore("myStore"),
                                Serdes.String(),
                                Serdes.Integer()
                        )
                        .withLoggingDisabled() // Changelog is not supported by MockProcessorContext.
                        .build();
        store.init(context, store);
        context.register(store, /*deprecated parameter*/  /*parameter unused in mock*/ null);

    }
}
