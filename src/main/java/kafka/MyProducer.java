package kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

/**
 * Description:
 *
 * @author zhangr
 * 2020/6/9 16:51
 */
@Slf4j
public class MyProducer {
    private KafkaProducer<String, String> producer;

    private void createProducer() {
        Properties kafkaProps = new Properties();
        //指定broker地址，不需要提供所有的地址，生产者可以连接给定的broker找到其他地址信息
        //但是至少提供两个避免宕机
        kafkaProps.put("bootstrap.servers", "server-1:9092,server-2:9092");
        //为键和值指定序列化器：默认的可以看org.apache.kafka.common.serialization.Serializer的继承树
        //基本类型都实现了，不用自己写
        kafkaProps.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(kafkaProps);
    }

    private void sendMsg() {
        //创建消息，指定主题、键值、消息内容
        ProducerRecord<String, String> record =
                new ProducerRecord<>("CustomerCountry", "Precision Products",
                        "France");
        try {
            //发送消息，用于不重要的消息日志
//            producer.send(record);
            //同步发送，拿到消息存储信息（偏移量、主题、分区）
//            RecordMetadata metadata = producer.send(record).get();
            //异步发送，指定回调函数
            producer.send(record, (recordMetadata, exception) -> {
                if (exception != null)
                    log.error(exception.getMessage());
                else
                    log.debug(recordMetadata.topic() + recordMetadata.offset() + recordMetadata.partition());
            });
        } catch (Exception e) {
            log.error("send error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

    }
}
