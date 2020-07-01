package kafka.producer.partition;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * 自定义分区器
 *
 * @author zhangr
 * 2020/6/10 10:05
 */
public class MyPartition implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        //获取指定主题的全部分区
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int partitionSize = partitions.size();

        if ((keyBytes == null) || !(key instanceof String)) {

        }

        if(((String)key).equals("AAA"))
            return partitionSize;   //放在最后一个分区
        return 0;//其他分配到分区0
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> configs) {
    }
}
