package com.zzh.kafka.consumer;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.zzh.kafka.producer.KafkaProducerClient2;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.*;

/**
 * @Description: offset 手动提交数据，指定时间 进行消费
 * @Author: zzh
 * @Crete 2022/12/14 14:21
 */
@Slf4j
public class ConsumerClient6 {
    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<ch.qos.logback.classic.Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> logger.setLevel(Level.INFO));
    }

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerClient2.class);

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //消费者组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer_group_1");
        //分区分配策略
        properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, RoundRobinAssignor.class.getName());


        //offset ,自动提交
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        try (KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties)) {
            HashSet<TopicPartition> partitions = new HashSet<>();
            partitions.add(new TopicPartition("test", 0));
            kafkaConsumer.assign(partitions);


            //指定某个分区的offset开始，进行数据的消费
            Set<TopicPartition> assignment = kafkaConsumer.assignment();

            //消费者启动的时候，需要和kafka进行大量的交互，来确定自己消费那个信息，在没有确定自己消费那个分区的时候，是无法获取到分区的信息的

            //保证消费者消费那个分区的策略已经分配完毕,拿到的分区信息不是空的
            while (CollectionUtils.isEmpty(assignment)) {
                kafkaConsumer.poll(Duration.ofSeconds(1));
                assignment = kafkaConsumer.assignment();
            }

            HashMap<TopicPartition, Long> timestampsToSearch = new HashMap<>();

            //指定消费者从一天前开始消费 数据
            assignment.forEach(topicPartition -> timestampsToSearch.put(topicPartition, System.currentTimeMillis() - 1 * 24 * 3600 * 1000));

            //获取固定时间的offset
            Map<TopicPartition, OffsetAndTimestamp> offsetAndTimestampMap = kafkaConsumer.offsetsForTimes(timestampsToSearch);

            assignment.forEach(topicPartition -> kafkaConsumer.seek(topicPartition, offsetAndTimestampMap.get(topicPartition).offset()));



            while (true) {
                ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));
                consumerRecords.forEach(consumerRecord -> logger.info("消费的数据：" + consumerRecord));

                //关闭自动提交

                //同步提交offset
//                kafkaConsumer.commitSync();
                //一步提交offset
                kafkaConsumer.commitAsync();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
