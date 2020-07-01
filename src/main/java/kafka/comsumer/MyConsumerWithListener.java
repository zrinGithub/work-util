package kafka.comsumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.*;

/**
 * Description:
 *
 * @author zhangr
 * 2020/6/10 11:43
 */
@Slf4j
public class MyConsumerWithListener {
    private KafkaConsumer<String, String> consumer;

    private void createConsumer() {
        Properties kafkaProps = new Properties();
        //指定broker地址，不需要提供所有的地址，生产者可以连接给定的broker找到其他地址信息
        //但是至少提供两个避免宕机
        kafkaProps.put("bootstrap.servers", "server-1:9092,server-2:9092");
        //指定消费组
        kafkaProps.put("group.id", "CountryCounter");
        //关闭自动提交偏移量
        kafkaProps.put("enable.auto.commit", "false");
        //为键和值指定反序列化器：默认的可以看org.apache.kafka.common.serialization.Serializer的继承树
        //基本类型都实现了，不用自己写
        kafkaProps.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProps.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<>(kafkaProps);
    }

    //记录偏移量
    private Map<TopicPartition, OffsetAndMetadata> currentOffset = new HashMap<>();
    int count = 0;

    private class HandleRebalance implements ConsumerRebalanceListener {
        /**
         * @param partitions
         */
        @Override
        public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
            log.debug("Lost partitions in rebalance . Committing current offset:" + currentOffset);
            consumer.commitSync(currentOffset);
        }

        @Override
        public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        }
    }

    //提交特定偏移量
    public void commitMsg() {
        try {
            consumer.subscribe(Collections.singletonList("myTopics"), new HandleRebalance());

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    //......处理消息
                    currentOffset.put(new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1, "metadata"));
                }
                consumer.commitAsync(currentOffset, null);
            }
        } catch (Exception e) {
            log.error("error", e);
        } finally {
            try {
                consumer.commitSync();
            } finally {
                consumer.close();
            }
        }
    }
}
