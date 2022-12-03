package com.zzh.kafka.streams.statestore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzh.kafka.KafkaStreamApplication;
import com.zzh.kafka.streams.module.SalesStatus;
import com.zzh.kafka.streams.serders.JsonSerdes;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreType;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import spark.Spark;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/2 01:25
 */
@Slf4j
public class StateStoreQueryServer {
    private final KafkaStreams kafkaStreams;
    private final String stateStore;

    public StateStoreQueryServer(KafkaStreams kafkaStreams, String stateStore) {
        this.kafkaStreams = kafkaStreams;
        this.stateStore = stateStore;
    }

    //default port:4567 ,curl -X GET http://localhost:4567/sales-status
    public void start() {
//        Spark.port(Integer.parseInt(System.getProperty("port")));
        Thread thread = new Thread(() -> {
            StoreQueryParameters<ReadOnlyKeyValueStore<String, SalesStatus>> parameters = StoreQueryParameters.fromNameAndType(stateStore, QueryableStoreTypes.keyValueStore());
            ReadOnlyKeyValueStore<String, SalesStatus> keyValueStore = kafkaStreams.store(parameters);
            Spark.get("/sales-status", ((request, response) -> {
                response.type("application/json");
                List<SalesStatus> result = new ArrayList<>();
                keyValueStore.all().forEachRemaining(value -> result.add(value.value));
                result.forEach(salesStatus -> log.info("" + salesStatus.toString()));
                return new ObjectMapper().writeValueAsBytes(result);
            }));
        }, "stateQuery");
        thread.setDaemon(true);
        thread.start();
    }
}
