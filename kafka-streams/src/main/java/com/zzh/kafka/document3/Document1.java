package com.zzh.kafka.document3;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;

import java.util.Properties;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/3/3 14:37
 */
public class Document1 {


    public static final String inputTopic = "test";

    public static void main(String[] args) {
        /**
         * KTable
         * */
        StreamsBuilder builder = new StreamsBuilder();
        KTable<String, String> firstKTable =
                builder.table(inputTopic,
                        Materialized.with(Serdes.String(), Serdes.String()));

        /**
         * global KTable
         * */
        StreamsBuilder builders = new StreamsBuilder();
        GlobalKTable<String, String> globalKTable =
                builders.globalTable(inputTopic,
                        Materialized.with(Serdes.String(), Serdes.String()));

        /**
         * KTable 和 GlobalKTable 之间的主要区别在于 KTable 在 Kafka Streams 实例之间分片数据，
         * 而 GlobalKTable 将数据的完整副本扩展到每个实例。您通常使用带有查找数据的 GlobalKTable。
         * 关于 GlobalKTable 和 KStream 之间的连接也有一些特殊之处；我们将在课程后面介绍这些内容。
         * */


        //请注意，对于状态存储，您将使用 Materialized 来提供 Serdes。

        /**
         * 对于输入输出用Consumed ， 对于有状体存储的过程的类型执行需要使用Materialized
         * StreamsBuilder builder = new StreamsBuilder()
         * KStream<String, MyObject> stream = builder.stream("topic",
         *     Consumed.with(Serdes.String(), customObjectSerde)
         *  自定义 SerDes：
         * 要创建自定义 SerDes，请使用工厂方法Serdes.serdeFrom并传递序列化器实例和反序列化器实例：
         *
         *Serde<T> serde = Serdes.serdeFrom( new CustomSerializer<T>,
         *     new CustomDeserializer<T>);
         *
         * */

        /**
         * join
         * 流流连接是窗口化的，但是流表连接不是窗口化的
         * 在流表连接中，流总是在主端；它驱动连接。只有到达流的新记录才会产生输出，而到达表的新记录则不会（KStream-KTable 和 KStream-GlobalKTable 连接都是这种情况）
         *
         * */

        /**
         * processor API ：处理器节点：
         *
         * Topology topology = new Topology();
         *
         * topology.addSource(“source-node”, “topicA”, ”topicB”);
         *
         * topology.addProcessor(“custom-processor”, new CustomProcessorSupplier(storeName), “source-node”);
         *
         * toplogy.addSink(“sink-node”, “output-topic”, “custom-processor”);
         *
         * DSL 提供了更加简单方便的处理方式，但是提供的场景不符合我们的场景的时候我们需要使用 processor APi
         *
         * */

        /**
         *
         * 测试 使用 ： TopologyTestDriver ，可以手动想 输入主题中添加内容，并从输出主题中获取结果，与期望的内容进行比较
         *
         *   try (final TopologyTestDriver testDriver = new TopologyTestDriver(builder.build(), streamsProps)) {
         *             final TestInputTopic<String, ElectronicOrder> inputTopic =
         *                     testDriver.createInputTopic(inputTopicName,
         *                             stringSerde.serializer(),
         *                             electronicSerde.serializer());
         *             final TestOutputTopic<String, Double> outputTopic =
         *                     testDriver.createOutputTopic(outputTopicName,
         *                             stringSerde.deserializer(),
         *                             doubleSerde.deserializer());
         *
         *             final List<ElectronicOrder> orders = new ArrayList<>();
         *             orders.add(ElectronicOrder.newBuilder().setElectronicId("one").setOrderId("1").setUserId("vandeley").setTime(5L).setPrice(5.0).build());
         *             orders.add(ElectronicOrder.newBuilder().setElectronicId("one").setOrderId("2").setUserId("penny-packer").setTime(5L).setPrice(15.0).build());
         *             orders.add(ElectronicOrder.newBuilder().setElectronicId("one").setOrderId("3").setUserId("romanov").setTime(5L).setPrice(25.0).build());
         *
         *             List<Double> expectedValues = List.of(5.0, 20.0, 45.0);
         *             orders.forEach(order -> inputTopic.pipeInput(order.getElectronicId(), order));
         *             List<Double> actualValues = outputTopic.readValuesToList();
         *             assertEquals(expectedValues, actualValues);
         *         }
         * */
        /**
         * kafkaStreams 错误处理：
         * Kafka Streams 有三大类错误：入口（消费者）错误、处理（用户逻辑）错误和出口（生产者）错误。
         *
         * 入口错误，通常是网络或反序列化错误。：DeserializationExceptionHandler ， 默认使用的是：LogAndFailExceptionHandler
         * streamsProps.put(StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG, StreamsDeserializationErrorHandler.class);
         *
         * 处理错误，与您提供的逻辑相关的任何异常最终都会冒泡并关闭应用程序：StreamsUncaughtExceptionHandler
         * kafkaStreams.setUncaughtExceptionHandler(new StreamsCustomUncaughtExceptionHandler());
         *
         * 出口错误，种类型的错误发生在将记录写入 Kafka 主题时，通常与网络或序列化错误有关：ProductionExceptionHandler  ，
         * streamsProps.put(StreamsConfig.DEFAULT_PRODUCTION_EXCEPTION_HANDLER_CLASS_CONFIG, StreamsRecordProducerErrorHandler.class);
         *
         * */

        /**
         * 主题
         * 分区
         * 任务
         * 线程
         * 实例
         * 消费者组 ，自动平衡
         * */
        /**
         * 状态容错：Kafka Streams 中的状态存储是持久的或内存中的。这两种类型都由变更日志主题支持以确保持久性。当 Kafka Streams 应用程序启动时，
         * 它会检测到一个有状态节点，如果它确定数据丢失，它将从 changelog 主题恢复
         * 当您设置num.standby.replicas为大于默认设置零时，Kafka Streams 指定另一个应用程序实例作为备用。
         * 备用实例通过读取变更日志使镜像存储与原始存储保持同步。当主实例宕机时，备用实例立即接管。
         *
         * */

        KafkaStreams kafkaStreams = new KafkaStreams(builders.build(), new Properties());
        kafkaStreams.close();
    }
}
