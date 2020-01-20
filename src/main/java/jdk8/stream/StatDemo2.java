package jdk8.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/20 15:05
 * }
 */
@Data
@AllArgsConstructor
class VideoOrder {
    private String tradeNo;
    private String title;
    private Integer money;
}

public class StatDemo2 {
    @Test
    public void testStat() {
        List<VideoOrder> orders = Arrays.asList(new VideoOrder("No123456", "redis", 10)
                , new VideoOrder("No123457", "springboot", 10)
                , new VideoOrder("No123458", "kafka", 40)
                , new VideoOrder("No123459", "hadoop", 50));
        List<VideoOrder> orders2 = Arrays.asList(new VideoOrder("No123456", "redis", 10)
                , new VideoOrder("No123401", "spring cloud", 10)
                , new VideoOrder("No123458", "kafka", 40)
                , new VideoOrder("No123450", "python", 100));

        //交集，这里实际上lombok实现了equals
        List<VideoOrder> intersectionList = orders.stream().filter(orders2::contains).collect(Collectors.toList());
        System.out.println("intersectionList:" + intersectionList);

        //差集
        List<VideoOrder> diffList = orders.stream().filter(obj -> !orders2.contains(obj)).collect(Collectors.toList());
        List<VideoOrder> diffList2 = orders2.stream().filter(obj -> !orders.contains(obj)).collect(Collectors.toList());
        System.out.println("diffList:" + diffList);
        System.out.println("diffList2:" + diffList2);

        //去重并集
        List<VideoOrder> videoOrders = orders.parallelStream().collect(Collectors.toList());
        videoOrders.addAll(orders2);
        List<VideoOrder> allOrders = videoOrders.parallelStream().distinct().collect(Collectors.toList());
        System.out.println("allOrders:" + allOrders);

        //两个订单平均价格
        Double videoAvg1 = orders.stream().collect(Collectors.averagingInt(VideoOrder::getMoney));
        System.out.println("videoAvg1:" + videoAvg1);
        Double videoAvg2 = orders2.stream().collect(Collectors.averagingInt(VideoOrder::getMoney));
        System.out.println("videoAvg2:" + videoAvg2);

        //订单总价
        Integer sum1 = orders.stream().collect(Collectors.summingInt(VideoOrder::getMoney));
        Integer sum2 = orders2.stream().collect(Collectors.summingInt(VideoOrder::getMoney));
        System.out.println("sum1:" + sum1);
        System.out.println("sum2:" + sum2);


    }
}
