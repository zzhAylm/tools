package com.zzh.kafka.document;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.time.Duration;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * @Description: TeatDirver
 * @Author: zzh
 * @Crete 2023/1/13 10:00
 */
public class KafkaStreams7 {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Long().getClass().getName());
//// Processor API
//        Topology topology = new Topology();
//        topology.addSource("sourceProcessor", "input-topic");
////        topology.addProcessor("processor"," fda", "sourceProcessor");
//        topology.addSink("sinkProcessor", "output-topic", "processor");
//// or
//// using DSL
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        streamsBuilder.stream("input-topic", Consumed.with(Serdes.String(), Serdes.String()).withName("input-topic-processor"))

                .to("output-topic");
        Topology topology = streamsBuilder.build();
//

//        // create test driver
        TopologyTestDriver testDriver = new TopologyTestDriver(topology, properties);


        TestInputTopic<String, Long> inputTopic = testDriver.createInputTopic("input-topic", Serdes.String().serializer(), Serdes.Long().serializer());

        inputTopic.pipeInput("key", 42L);
        inputTopic.pipeInput("zzh", 42L);
        inputTopic.pipeInput("ylm", 42L);

        TestOutputTopic<String, Long> outputTopic = testDriver.createOutputTopic("output-topic", Serdes.String().deserializer(), Serdes.Long().deserializer());

//        System.out.printf("比较结果%s", outputTopic.readKeyValue().equals(new KeyValue<>("key", 42L)));
//        System.out.printf("%s", outputTopic.isEmpty());

        while (!outputTopic.isEmpty()){
            KeyValue<String, Long> keyValue = outputTopic.readKeyValue();
            System.out.println("key="+keyValue.key+",value="+keyValue.value);
        }


        testDriver.advanceWallClockTime(Duration.ofSeconds(20));

        testDriver.close();
    }
}
