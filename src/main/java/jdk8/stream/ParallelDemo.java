package jdk8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/20 10:11
 * }
 */
public class ParallelDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);

        //顺序显示
//        list.stream().forEach(System.out::println);
        //随机顺序，因为是并行执行的
        list.parallelStream().forEach(System.out::println);

        //多线程执行可能产生的问题
        for (int i = 0; i < 10; i++) {
            //产生线程不安全的问题    抛出ArrayIndexOutOfBoundsException
//            List<Integer> addList = new ArrayList<>();
            //使用线程安全的列表
            List<Integer> addList = new CopyOnWriteArrayList<>();
            IntStream.range(0, 100).parallel().forEach(addList::add);
            System.out.println(addList.size());
            System.out.println("addList:" + addList);
        }
    }
}
