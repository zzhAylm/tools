package com.zzh.kafka.document3;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;

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
    }
}
