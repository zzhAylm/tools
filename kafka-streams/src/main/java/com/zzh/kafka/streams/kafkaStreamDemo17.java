package com.zzh.kafka.streams;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.zzh.kafka.streams.module.Patient;
import com.zzh.kafka.streams.module.PatientWithSickRome;
import com.zzh.kafka.streams.module.SickRome;
import com.zzh.kafka.streams.serders.JsonSerdes;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.apache.kafka.streams.state.KeyValueStore;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: 心跳检测
 * @Author: zzh
 * @Crete 2022/12/3 23:14
 */
@Slf4j
public class kafkaStreamDemo17 {
    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> logger.setLevel(Level.INFO));
    }

    private static final String APPLICATION_ID = "heart-beat-processor";
    private static final String PATIENT_TOPIC = "patient";
    private static final String HEART_BEAT_TOPIC = "heartBeat";
    private static final String SICK_ROME_TOPIC = "sickRome";
    private static final String BOOTSTRAP_SERVER = "127.0.0.1:9092";

    public static void main(String[] args) {
        Properties properties = new Properties();

        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_ID);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        properties.put(StreamsConfig.STATE_DIR_CONFIG, "/Users/zzh/Company/tools/kafka_2.12-3.3.1/data/window-operation");
        properties.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 3);
        properties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);
        properties.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class.getName());

        StreamsBuilder streamsBuilder = new StreamsBuilder();

        KStream<String, Long> heatBeatKs = streamsBuilder
                .stream(HEART_BEAT_TOPIC, Consumed.with(Serdes.String(), Serdes.String())
                        .withName("heat-beat-processor")
                        .withOffsetResetPolicy(Topology.AutoOffsetReset.LATEST))
                .groupBy((k, v) -> v, Grouped.with(Serdes.String(), Serdes.String()))
                .windowedBy(TimeWindows.ofSizeAndGrace(Duration.ofMinutes(1), Duration.ofSeconds(1)))
                .count(Named.as("heart-beat-count"), Materialized.as("heart-beat-state-store"))
                .suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded()))
                .toStream()
                .peek((k, v) -> log.info("pre filter,k={},v={}", k, v))
                .filter(((key, value) -> value > 80))
                .peek((k, v) -> log.info("after filter,k={},v={}", k, v))
                .selectKey((k, v) -> k.key());

        KTable<String, Patient> patientKT = streamsBuilder
                .table(PATIENT_TOPIC, Consumed.with(Serdes.String(), JsonSerdes.patientSerde())
                        .withName("patient-key-table")
                        .withOffsetResetPolicy(Topology.AutoOffsetReset.LATEST));

        KTable<String, SickRome> sickRomeKT = streamsBuilder
                .table(SICK_ROME_TOPIC, Consumed.with(Serdes.String(), JsonSerdes.sickRomeSerde())
                        .withName("sick-rome-key-table")
                        .withOffsetResetPolicy(Topology.AutoOffsetReset.LATEST));


        KTable<String, PatientWithSickRome> patientWithSickRomeKT = patientKT.join(
                sickRomeKT
                , Patient::getSickRomeId
                , PatientWithSickRome::new
                , Named.as("patientKt-sickRomeKt")
                , Materialized.as("patientKt-sickRomeKt-state-store"));

        heatBeatKs.join(patientWithSickRomeKT, (heat, patientWithSickRome) -> {
                    patientWithSickRome.setHeatBeat(heat);
                    return patientWithSickRome;
                }, Joined.with(Serdes.String(), Serdes.Long(), JsonSerdes.patientWithSickRomeSerde()))
                .peek((k, v) -> log.info("k={},v={}", k, v))
                .filter(((key, value) -> value.getHeatBeat() > 80 && value.getPatient().getAge() > 25))
                .print(Printed.<String, PatientWithSickRome>toSysOut().withLabel("heat-beat-print"));


        Topology topology = streamsBuilder.build();
        final KafkaStreams kafkaStreams = new KafkaStreams(topology, properties);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            kafkaStreams.close();
            countDownLatch.countDown();
            log.info("kafka stream close！");
        }));
        try {
            kafkaStreams.start();
            countDownLatch.await();
        } catch (Throwable e) {
            System.exit(1);
        }
        System.exit(0);
    }
}
