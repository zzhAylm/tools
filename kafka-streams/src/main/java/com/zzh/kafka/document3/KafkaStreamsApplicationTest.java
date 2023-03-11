package com.zzh.kafka.document3;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class KafkaStreamsApplicationTest {
    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> logger.setLevel(Level.INFO));
    }
    @Test
    public void topologyShouldUpperCaseInputs() {

        final Properties props = new Properties();
        final String inputTopicName = "input";
        final String outputTopicName = "output";
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "KafkaStreamsApplicationTest");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.STATE_DIR_CONFIG, "/Users/zzh/Company/tools/kafka_2.12-3.3.1/data");
        props.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 3);

        final Topology topology = KafkaStreamsApplication.buildTopology("input", "output");

        try (final TopologyTestDriver testDriver = new TopologyTestDriver(topology, props)) {
            Serde<String> stringSerde = Serdes.String();
            final TestInputTopic<String, String> inputTopic = testDriver
                    .createInputTopic(inputTopicName, stringSerde.serializer(), stringSerde.serializer());
            final TestOutputTopic<String, String> outputTopic = testDriver
                    .createOutputTopic(outputTopicName, stringSerde.deserializer(), stringSerde.deserializer());

            List<String> inputs = Arrays.asList(
                    "Chuck Norris can write multi-threaded applications with a single thread.",
                    "No statement can catch the ChuckNorrisException.",
                    "Chuck Norris can divide by zero.",
                    "Chuck Norris can binary search unsorted data."
            );
            List<String> expectedOutputs = inputs.stream()
                    .map(String::toUpperCase).collect(Collectors.toList());

            inputs.forEach(inputTopic::pipeInput);
            final List<String> actualOutputs = outputTopic.readValuesToList();

            assertThat(expectedOutputs, equalTo(actualOutputs));

        }

    }
}
