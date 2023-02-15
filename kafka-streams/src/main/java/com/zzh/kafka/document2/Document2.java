package com.zzh.kafka.document2;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;

import java.util.Map;

/**
 * @Description: 分支转换
 * @Author: zzh
 * @Crete 2023/2/13 13:52
 */
public class Document2 {

    /**
     * KStream -> KTable
     * KStream -> BranchedKStream
     */
    public static void main(String[] args) {


        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, String> fromStream = streamsBuilder.stream("test", Consumed.with(Serdes.String(), Serdes.String()));

        // 流分支 split()
        Map<String, KStream<String, String>> stringKStreamMap = fromStream.split(Named.as("-"))
                .branch(((key, value) -> key.startsWith("A")), Branched.as("AStreams"))
                .branch((key, value) -> key.startsWith("B"), Branched.as("BStreams"))
                .defaultBranch(Branched.as("OtherStreams"));

        // flatMap(): 获取一条记录并生成零条、一条或多条记录 ，将流标记为数据重新分区： 只有应用分组或者连接会导致记录的重新分区

        // flatMapValues() :取一条记录并生成零条、一条或多条记录，同时保留原始记录的密钥，flatMapValues优于，flatMap因为它不会导致数据重新分区，但是他不能更改key

        // GroupByKey(): 分组是聚合流或表的先决条件，并确保数据被正确分区（“键控”）以用于后续操作。当且仅当流被标记为重新分区时才会导致数据重新分区

        // Group the stream by a new key and key type
        KGroupedStream<String, String> groupedStream = fromStream.groupBy(
                (key, value) -> value,
                Grouped.with(
                        Serdes.String(), /* key (note: type was modified) */
                        Serdes.String())  /* value */
        );
        // GroupBy: groupBy总是导致数据重新分区。如果可能，请改用groupByKey，它只会在需要时重新分区数据。GroupBy=selectKey()+groupByKey()


//        KGroupedStream<String, String> groupedStream1 = stringKStreamMap.get("A").groupByKey();
//        KGroupedStream<String, String> groupedStream2 = stringKStreamMap.get("B").groupByKey();
//        CogroupedKStream<byte[], String> cogroupedStream = groupedStream1.cogroup((k,v,a)->{
//        }).cogroup(groupedStream2, groupedStream2);
        //Cogroup 不会导致重新分区，因为它具有输入流被分组的先决条件。在创建这些组的过程中，如果流已标记为重新分区，则它们将已经重新分区。

        // selectKey : 选择建，将流标记为数据重新分区,之后应用分组或连接,将导致记录的重新分区。

        // KStream->KTable

        // repartition():使用所需的分区数手动触发流的重新分区,repartition()操作总是触发流的重新分区，因此它可以与嵌入式处理器 API 方法（如等transform()）一起使用，
        // 这些方法在预先执行密钥更改操作时不会触发自动重新分区。

        //transform ,transformValues 等操作之前需要手动触发重新分区，不然会， 在使用这种嵌入式的处理器API方法之前需要触发重新分区

        /**
         * // Aggregating a KGroupedTable (note how the value type changes from String to Long)
         * KTable<byte[], Long> aggregatedTable = groupedTable.aggregate(
         *     () -> 0L,
         *(aggKey, newValue, aggValue) -> aggValue + newValue.length(),
         *(aggKey, oldValue, aggValue) -> aggValue - oldValue.length(),
         *Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("aggregated-table-store")
         * 	.withValueSerde(Serdes.Long())
         * */
        // 聚合：Aggregating ，聚合只能操作KGroupStreams  KGroupTable

        /**
         * KTable<String, Long> aggregatedTable = groupedTable.reduce(
         *     (aggValue, newValue) -> aggValue + newValue,
         * (aggValue, oldValue) -> aggValue - oldValue);
         * */
        // 聚合的方式：Aggregating ，count，Reduce（与Aggregating相比，归约返回的类型不能变），是aggregation的缩减版

        /**
         * 标记数据为重新分区的方法：selectKey ， flatMap,transform ,
         * groupBy:总是导致重新分区， groupByKey,  ： 只有数据被标记为重新分区的时候才会导致重新分区
         *
         * 如果数据被标记为重新分区，进行此操作会重新分区，如：分组groupByKey，连接inner join
         * */
        // inner join： 数据必须共同分区：双方的输入数据必须共同分区。当且仅当流被标记为重新分区（如果两者都被标记，则两者都被重新分区）时，才会导致流的数据重新分区。

        // left join: null具有键或值的输入记录null将被忽略，并且不会触发连接。

        /**
         * KGroupedTable<String, String> groupedTable = ...;
         * groupedTable
         *     .count()
         *     .suppress(untilTimeLimit(ofMinutes(5), maxBytes(1_000_000L).emitEarlyWhenFull()))
         *     .toStream()
         *     .foreach((key, count) -> updateCountsDatabase(key, count));
         * */

        //控制 KTABLE 发射率 ：此配置可确保每 5 分钟不超过一次updateCountsDatabase获取事件。key请注意，
        // 每个键的最新状态必须在内存中缓冲 5 分钟。您可以选择控制用于此缓冲区的最大内存量（在本例中为 1MB）。
        // 还有一个选项可以根据记录数量施加限制（或不指定两个限制）。


   }
}
