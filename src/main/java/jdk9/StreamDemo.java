package jdk9;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/22 17:47
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<String> list = List.of("a", "ab", "abc");
        List<String> takeWhile = list.stream().takeWhile(obj -> obj.length() < 2).collect(Collectors.toList());
        System.out.println(takeWhile);
        List<String> dropWhile = list.stream().dropWhile(obj -> obj.length() < 2).collect(Collectors.toList());
        System.out.println(dropWhile);

    }
}
