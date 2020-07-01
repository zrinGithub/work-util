package kafka.producer.serialize;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;

/**
 * Description:
 * 针对Customer的序列化器
 *
 * @author zhangr
 * 2020/6/10 9:33
 */
public class CustomerSerializer implements Serializer<Customer> {
    @Override
    public byte[] serialize(String s, Customer customer) {
        try {
            byte[] serializedName;
            int nameSize;
            if (customer == null) return null;
            else {
                if (customer.getName() != null) {
                    serializedName = customer.getName().getBytes("UTF-8");
                    nameSize = serializedName.length;
                } else {
                    serializedName = new byte[0];
                    nameSize = 0;
                }
            }
            //id占据4个字节
            //nameSize4个字节
            ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + nameSize);
            buffer.putInt(customer.getId());
            buffer.putInt(nameSize);
            buffer.put(serializedName);
            return buffer.array();
        } catch (Exception e) {
            throw new SerializationException(e);
        }
    }
}
