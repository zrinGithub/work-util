package jdk8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/14 10:05
 * }
 */
public class LambdaDemo {
    public static void main(String[] args) {
        //创建线程：
        new Thread(() -> System.out.println("hello world")).start();

        //排序
        List<String> list = Arrays.asList("ccc", "adg", "agh");
        Collections.sort(list, String::compareTo);
        System.out.println(list);
        //只是展示写法，实际上可以写Collections.sort(list,Collections.reverseOrder());
        Collections.sort(list, (i, j) -> j.compareTo(i));
        System.out.println(list);
    }
}
