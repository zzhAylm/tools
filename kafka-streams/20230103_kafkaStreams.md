20230103:

![image-20230103152354673](/Users/zzh/Library/Application Support/typora-user-images/image-20230103152354673.png)

Source topic 又个几个partition就会创建几个task，默认是单个线程处理多个task，可以通过增加线程数量来提升并发度 

![image-20230103153300473](/Users/zzh/Library/Application Support/typora-user-images/image-20230103153300473.png)



```java
aggregate:
// Aggregating with time-based windowing (here: with 5-minute tumbling windows)
KTable<Windowed<String>, Long> timeWindowedAggregatedStream = groupedStream.windowedBy(Duration.ofMinutes(5))
    .aggregate(
        () -> 0L, /* initializer */
        (aggKey, newValue, aggValue) -> aggValue + newValue, /* adder */
        Materialized.<String, Long, WindowStore<Bytes, byte[]>>as("time-windowed-aggregated-stream-store") /* state store name */
        .withValueSerde(Serdes.Long())); /* serde for aggregate value */

count:
KTable<Windowed<String>, Long> aggregatedStream = groupedStream.windowedBy(
    TimeWindows.ofSizeWithNoGrace(Duration.ofMinutes(5))) /* time-based window */
    .count();

reduce:
KTable<Windowed<String>, Long> timeWindowedAggregatedStream = groupedStream.windowedBy(
  TimeWindows.ofSizeWithNoGrace(Duration.ofMinutes(5)) /* time-based window */)
  .reduce(
    (aggValue, newValue) -> aggValue + newValue /* adder */
  );

aggregated:
KTable<String, Integer> aggregated = groupedStream.aggregate(
    () -> 0, /* initializer */
    (aggKey, newValue, aggValue) -> aggValue + newValue, /* adder */
    Materialized.<String, Long, KeyValueStore<Bytes, byte[]>as("aggregated-stream-store" /* state store name */)
      .withKeySerde(Serdes.String()) /* key serde */
      .withValueSerde(Serdes.Integer()); /* serde for aggregate value */
```



```
20201016_商业支付中心统一分户代码合并_赵迎超
```

