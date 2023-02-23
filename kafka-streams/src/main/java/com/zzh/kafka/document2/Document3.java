package com.zzh.kafka.document2;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.bouncycastle.util.Bytes;

import java.time.Duration;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/2/14 10:09
 */
public class Document3 {

    /**
     * 标记为重新分区： flatMap ，selectKey
     *
     * 如果数据被标记为重新分区后，分组和连接操作（join ）会直接导致重新分区（groupBy 无论是否被标记都会重新分区，groupByKey 只有数据被标记为重新分区是，才会导致分区）
     *
     * repartition() ： 重新分区操作，与嵌入式处理器 API 方法一起使用，例如transform() ,
     *
     *  KStream : transform()（可以查询本地存储） 、repartition() 、join()、process()、repartition()
     *
     *  KGroupStreams: reduce()  、aggregate()、windowedBy()
     *
     * 轻量级锁：cas 自旋等待
     * 重量级锁： 中断线程，等待唤醒
     *
     *
     *
     * */
    public static void main(String[] args) {

        StreamsBuilder streamsBuilder=new StreamsBuilder();

        KStream<Object, Object> stream = streamsBuilder.stream("");

        KGroupedStream<Object, Object> objectObjectKGroupedStream = stream.groupByKey();


//        KStream<String, Long> left = ...;
//        GlobalKTable<Integer, Double> right = ...;
//        // Java 8+ example, using lambda expressions
//        KStream<String, String> joined = left.join(right,
//                (leftKey, leftValue) -> leftKey.length(), /* derive a (potentially) new key by which to lookup against the table */
//                (leftValue, rightValue) -> "left=" + leftValue + ", right=" + rightValue /* ValueJoiner */
//        );

//        KStream<String, Long> left = ...;
//        KTable<String, Double> right = ...;

// Java 8+ example, using lambda expressions
//        KStream<String, String> joined = left.leftJoin(right,
//                (leftValue, rightValue) -> "left=" + leftValue + ", right=" + rightValue, /* ValueJoiner */
//                Joined.keySerde(Serdes.String()) /* key */
//                        .withValueSerde(Serdes.Long()) /* left value */
//        );

        /**
         * 开窗 : 开窗让您可以控制如何将具有相同键的记录分组以进行有状态操作，例如 聚合或连接到所谓的窗口中。Windows 按记录键进行跟踪。
         *
         * Tumbling time window : 翻转窗口，固定大小、不重叠、无间隙的窗口
         * Hopping time window : 跳跃窗口，翻转窗口，固定大小，重叠
         * Sliding time window: 滑动窗口，固定大小、重叠的窗口，用于处理记录时间戳之间的差异
         *
         *
         * Session window : 回话窗口，动态大小、非重叠、数据驱动的窗口
         * */
//
//        KGroupedStream<UserId, Event> grouped = ...;
//        grouped
//                .windowedBy(TimeWindows.of(Duration.ofHours(1)).grace(Duration.ofMinutes(10)))
//                .count()
//                .suppress(Suppressed.untilWindowCloses(unbounded()))
//                .filter((windowedUserId, count) -> count < 3)
//                .toStream()
//                .foreach((windowedUserId, count) -> sendAlert(windowedUserId.window(), windowedUserId.key(), count));

        /**
         * grace(Duration.ofMinutes(10)) :
         * 这允许我们限制窗口将接受的事件的无序（或延迟）。例如，09:00 到 10:00 窗口将接受乱序记录，直到 10:10，此时窗口关闭。
         *
         * .suppress(Suppressed.untilWindowCloses(...))
         * 这会将抑制运算符配置为在窗口关闭之前不为窗口发出任何内容，然后发出最终结果。例如，如果用户U在 09:00 和 10:10 之间获得 10 个事件，
         * 则filter抑制的下游将在 10:10 之前获得窗口键的任何事件@09:00-10:00，然后它只会获得一个值为 的事件10。这是加窗计数的最终结果。
         *
         * unbounded()
         * 这配置了用于存储事件的缓冲区，直到它们的窗口关闭。生产代码能够限制用于缓冲区的内存量，但这个简单的示例创建了一个没有上限的缓冲区。
         *
         * */

        /**
         * 有状态的操作，不会立即出现结果，因为KafkaStreams 有一个内部缓冲机制：当缓存满时，或者到30秒后调用一次，此时才会看到更新的结果
         *
         * Materialized.with(Serdes.String(), Serdes.Double()))
         * .suppress(untilWindowCloses(unbounded()))
         * 这给出了窗口聚合的单一结果。（该unbounded参数表示缓冲区将根据需要继续消耗内存，直到窗口关闭。）抑制是可选的，特别是如果您想查看任何中间结果。
         * */

//        KStream<String, String> myStream = builder.stream("topic-A");
//        Duration timeDifference = Duration.ofSeconds(2);
//        Duration gracePeriod = Duration.ofMillis(500);
//        myStream.groupByKey()
//                .windowedBy(SlidingWindows.withTimeDifferenceAndGrace(timeDifference, gracePeriod))
//                .count();



    }
}
