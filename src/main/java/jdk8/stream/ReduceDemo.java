package jdk8.stream;

import java.util.stream.Stream;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/20 10:44
 * }
 */
public class ReduceDemo {
    public static void main(String[] args) {
        //reduce 的参数 BinaryOperator<T> extends BiFunction<T,T,T>
        //reduce可以增加初始值
//        Integer count = Stream.of(1, 2, 4, 5).reduce((a, b) -> a + b).get();
        Integer count = Stream.of(1, 2, 4, 5).reduce(100, (a, b) -> a + b);
        System.out.println(count);
    }
}
