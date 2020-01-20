package jdk8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/20 10:53
 * }
 */
public class ForeachDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        //forEach参数 Consumer
        list.forEach(System.out::println);
    }
}
