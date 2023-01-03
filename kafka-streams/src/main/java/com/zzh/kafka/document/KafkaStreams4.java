package com.zzh.kafka.document;

/**
 * @Description: kafkaStream并行度
 * @Author: zzh
 * @Crete 2023/1/3 10:54
 */
public class KafkaStreams4 {

    //kafkaStreams 并行度：kafka topic有多少个分区就会创建多少个task，
    //如果不设置的话我们的会是单线程处理多个task
    //如果向增加并行度，可以增加线程数量，一个线程处理的task数量越少，并行度越高
    //server（一台机器）>instance（一个实例）>thread（一个线程）>task（一个任务）=partition（一个分区）
    //多台机器上运行，每台机器运行多个实例，每个实例运行多个线程，每一个线程运行多个任务，每个任务处理一个topic分区
    //kafka严格遵行顺序，不会有顺序问题
    //摄取时间：消息或记录被存储到kafka分区的时间
    //处理时间：消息被消费的时间
    //事件时间：时间发生的时间
    public static void main(String[] args) {

    }
}
