package com.zzh.kafka.document2;

import org.apache.kafka.common.serialization.Serdes;
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


        // 缓存：
        /**
         * 实例每个线程之间共享，
         *
         * 缓存具有三个功能。首先，它用作读取缓存以加速从状态存储中读取数据。
         * 其次，它充当状态存储的回写缓冲区。回写缓存允许批处理多个记录，而不是将每个记录单独发送到状态存储。
         * 它还减少了进入状态存储的请求数量（如果它是持久状态存储，则它的更改日志主题存储在 Kafka 中）因为具有相同键的记录被压缩在缓存中。
         * 第三，回写缓存也减少了进入下游处理器节点的记录数量。
         * */

        //容错：
        /**
         * Kafka Streams 建立在 Kafka 中本地集成的容错功能之上。Kafka 分区是高可用和可复制的；
         * 因此，当流数据持久化到 Kafka 时，即使应用程序失败并需要重新处理它也是可用的。
         * Kafka Streams 中的任务利用Kafka 消费者客户端提供的容错功能来处理故障。
         * 如果任务在失败的机器上运行，Kafka Streams 会自动在应用程序的剩余运行实例之一中重新启动任务
         *
         * 此外kafka还为每个kafkastreams的任务的本地存储 维护了一个变更日志主题，如果一个kafkaStreams发生故障可以通过kafka的变更日志恢复到故障前
         *
         *
         * */

        /**
         * 状态存储
         * 在有状态操作中，您通常首先按键分组，因此必须存在键。如果不是，您可以导出一个密钥，但您将不得不重新分区。重新分区基本上是写回主题的行为，以便键最终位于正确的分区上。
         * 如果您创建一个新密钥，它很可能不在它所属的分区上。
         *
         * Kafka Streams 中的有状态操作由状态存储支持。默认是在 RocksDB 中实现的持久状态存储，但您也可以使用内存存储。状态存储由变更日志主题备份，使 Kafka Streams 中的状态具有容错性。
         * 当您调用有状态操作时，将返回一个 KTable（回想一下，在表中，新值会覆盖以前的值）。Kafka Streams 中的有状态操作包括reduce、count和aggregate。
         * */
        /**
         * reduce:
         * StreamsBuilder builder = new StreamsBuilder();
         * KStream<String, Long> myStream = builder.stream("topic-A");
         * Reducer<Long> reducer = (longValueOne, longValueTwo) -> longValueOne + longValueTwo;
         * myStream.groupByKey().reduce(reducer,
         *                              Materialized.with(Serdes.String(), Serdes.Long()))
         *                             .toStream().to("output-topic");
         *
         * Aggregate是 的一种形式reduce，但使用aggregate，您可以返回不同的类型
         *
         * StreamsBuilder builder = new StreamsBuilder();
         * KStream<String, String> myStream = builder.stream("topic-A");
         *
         * Aggregator<String, String, Long> characterCountAgg =
         *     (key, value, charCount) -> value.length() + charCount;
         * myStream.groupByKey().aggregate(() -> 0L,
         *                                       characterCountAgg,
         *                                       Materialized.with(Serdes.String(), Serdes.Long()))
         *                                       .toStream().to("output-topic");
         *
         *
         * 注意事项：
         * 有状态操作不会立即发出结果。Kafka Streams 有一个缓存结果的内部缓冲机制。有两个因素控制缓存何时发出记录：当缓存已满时发出记录（在存储数量中每个实例平均定义；它是 10MB），
         * 默认情况下，Kafka Streams 每 30 秒调用一次（你不调用commit自己commit） . 此时，您会看到更新。为了查看通过聚合进行的每个更新，您可以将缓存大小设置为零（这对于调试也很有用）。
         * 即使使用缓存，您也会得到多个结果，因此对于单个和最终的有状态结果，您应该对操作使用抑制重载aggregate/reduce。
         *
         * 默认的窗口是30秒，缓存
         * */
        /**
         * 分组 + 聚合，统计，归约
         * 分组 + 开窗 + 聚合，统计，归约
         *
         * winding:
         * tumbling 翻转窗口 TimeWindows tumblingWindow = TimeWindows.of(windowSize);
         * hopping  跳跃窗口 TimeWindows hoppingWindow = TimeWindows.of(windowSize).advanceBy(advanceSize);
         * sliding  随时间移动，当数据有变化的时候才会形成新窗口 ：windowedBy(SlidingWindows.withTimeDifferenceAndGrace(timeDifference, gracePeriod))
         * session .windowedBy(SessionWindows.with(inactivityGap))
         *
         *
         * 执行一个groupByKey，并添加一个一小时的滚动窗口，宽限期为五分钟：
         * electronicStream.groupByKey().windowedBy(TimeWindows.of(Duration.ofHours(1)).grace(Duration.ofMinutes(5)))
         *
         * 为状态存储使用的聚合中的类型添加 SerDes。然后，添加一个抑制运算符，以便在窗口关闭之前不发出任何更新：
         *
         * Materialized.with(Serdes.String(), Serdes.Double())) .suppress(untilWindowCloses(unbounded()))
         * 这给出了窗口聚合的单一结果。（该unbounded参数表示缓冲区将根据需要继续消耗内存，直到窗口关闭。）抑制是可选的，特别是如果您想查看任何中间结果。
         * */
        /**
         * 时间戳概念：
         *
         * 活动时间
         * 使用事件时间，如果您不添加自己的时间，生产者会使用生产者环境的当前时间自动创建时间戳。
         *
         * 日志追加时间
         * 使用日志附加时间，当记录到达代理时，代理将在将记录附加到日志时用自己的时间戳（代理环境的当前时间）覆盖生产者记录的时间戳
         *
         * kafka streams 流的时间，默认是使用时间时间， 我们可以配置为日志追加时间（事件到达 stream 的时间，会覆盖掉流元素的事件时间）、
         * 窗口化操作是由记录时间戳驱动的，而不是由挂钟时间驱动的
         * 在Kafka Streams中，首先选择所有分区中最早的时间戳进行处理
         * Kafka Streams使用该TimeStampExtractor接口从当前记录中获取时间戳。
         *
         * 如果您想使用嵌入在记录键或值本身中的时间戳，您可以提供一个自定义的TimeStampExtractor.
         *
         * 默认行为是使用来自 a 的时间戳ConsumerRecord，它具有由生产者或代理设置的时间戳。
         * 的默认实现TimeStampExtractor是FailOnInvalidTimestamp，这意味着如果你得到一个小于零的时间戳，它会抛出异常
         *
         * 学习如何使用自定义TimestampExtractor来驱动 Kafka Streams 应用程序的行为，使用嵌入在记录本身中的时间戳。
         *
         * Consumed.with(Serdes.String(), electronicSerde)
         *     .withTimestampExtractor(new OrderTimestampExtractor()))
         *     .peek((key, value) -> System.out.println("Incoming record - key " +key +" value " + value));
         * */
    }
}
