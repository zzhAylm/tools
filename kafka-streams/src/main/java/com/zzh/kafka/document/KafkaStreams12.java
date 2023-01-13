package com.zzh.kafka.document;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;

import java.util.Properties;

/**
 * @Description: 内存管理
 * @Author: zzh
 * @Crete 2023/1/13 15:34
 */
public class KafkaStreams12 {

    public static void main(String[] args) {
        Properties props = new Properties();
        //DSL中关闭缓存
//        props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);

        //要启用缓存但仍对记录缓存多长时间有上限，您可以设置提交间隔
        props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 10 * 1024 * 1024L);
        // Set commit interval to 1 second.
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 1000);

        // 处理器API中记录缓存
        StoreBuilder countStoreBuilder =
                Stores.keyValueStoreBuilder(
                                Stores.persistentKeyValueStore("Counts"),
                                Serdes.String(),
                                Serdes.Long())
                        .withCachingEnabled();

    }
}
