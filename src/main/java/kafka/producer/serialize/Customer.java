package kafka.producer.serialize;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Description:
 *
 * @author zhangr
 * 2020/6/10 9:32
 */
@Data
@AllArgsConstructor
public class Customer {
    private int id;
    private String name;
}
