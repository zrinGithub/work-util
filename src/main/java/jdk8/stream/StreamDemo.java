package jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/14 15:26
 * }
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("KL1", "as");
        List<String> strings = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<Integer> length = list.stream().map(String::length).collect(Collectors.toList());
        System.out.println(strings);
        System.out.println(length);
    }
}
