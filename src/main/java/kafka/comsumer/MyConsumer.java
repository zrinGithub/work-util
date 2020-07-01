package kafka.comsumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Description:
 *
 * @author zhangr
 * 2020/6/10 11:43
 */
@Slf4j
public class MyConsumer {
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

    private void subscribe() {
        consumer.subscribe(Collections.singletonList("myTopics"));


    }

    public void consume() {
        try {
            //无限循环->消费者本来就是一个长期运行的应用程序
            while (true) {
                //保持轮询，否则被认为已经死亡
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                //记录列表
                for (ConsumerRecord<String, String> record : records) {
                    log.debug("topic = %s, " +
                                    "partition = %s, " +
                                    "offset = %d, " +
                                    "customer = %s," +
                                    "country = %s\n",
                            record.topic(),
                            record.partition(),
                            record.offset(),
                            record.key(),
                            record.value());
                }
            }
        } finally {
            consumer.close();
        }
    }

    public void consumeCommitSync() {
        //无限循环->消费者本来就是一个长期运行的应用程序
        while (true) {
            //保持轮询，否则被认为已经死亡
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            //处理记录
            for (ConsumerRecord<String, String> record : records) {
                //
            }
            try {
                consumer.commitSync();
            } catch (CommitFailedException e) {
                log.error("commit failed", e);
            }
        }
    }

    public void consumeCommitASync() {
        try {
            //无限循环->消费者本来就是一个长期运行的应用程序
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    //处理消息
                }
                try {
                    //异步提交
//                consumer.commitAsync();
                    consumer.commitAsync(((offsets, exception) -> {
                        if (exception != null)
                            log.error("error", offsets, exception);
                        //offsets的key就是主题分区，value是偏移量和元数据
                        log.debug(offsets.keySet().toString());
                    }));
                } catch (CommitFailedException e) {
                    log.error("commit failed", e);
                }
            }
        } catch (Exception e) {
            log.error("error", e);
        } finally {
            consumer.close();
        }
    }

    //同步异步组合提交
    public void commitBeforeClose() {
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    //处理消息
                }
                try {
                    //这里使用异步提交，比较阻塞，速度更快
                    consumer.commitAsync();
                } catch (CommitFailedException e) {
                    log.error("commit failed", e);
                }
            }
        } catch (Exception e) {
            log.error("error", e);
        } finally {
            try {
                //关闭消费者之前，没有下一次提交来保障，所以必须使用同步，不断重试直到成功
                consumer.commitSync();
            } finally {
                consumer.close();
            }
        }
    }

    //记录偏移量
    private Map<TopicPartition, OffsetAndMetadata> currentOffset = new HashMap<>();
    int count = 0;

    //提交特定偏移量
    public void commitMsg() {


        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    //......处理消息
                    currentOffset.put(new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1, "metadata"));
                    //10000条数据异步提交一次
                    if (count % 1000 == 0)
                        consumer.commitAsync(currentOffset, null);//不提供回调函数
                    count++;
                }
            }
        } catch (Exception e) {
            log.error("error", e);
        } finally {
            try {
                //关闭消费者之前，没有下一次提交来保障，所以必须使用同步，不断重试直到成功
                consumer.commitSync();
            } finally {
                consumer.close();
            }
        }
    }
}
