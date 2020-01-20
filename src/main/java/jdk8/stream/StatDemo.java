package jdk8.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/20 10:59
 * }
 */
@Data
@AllArgsConstructor
class Student {
    private String name;
    private Integer age;
    private Double score;
}

public class StatDemo {
    @Test
    public void testCollector() {
        List<String> list = Arrays.asList("KL1", "as");
        List<String> strings = list.stream().collect(Collectors.toList());
        LinkedList<String> strings1 = list.stream().collect(Collectors.toCollection(LinkedList::new));
        System.out.println(strings);
        System.out.println(strings1);
    }

    @Test
    public void testJoining() {
        List<String> list = Arrays.asList("KL1", "as", "kage");
        String collect = list.stream().collect(Collectors.joining(",", "[", "]"));
        System.out.println(collect);
    }

    //使用参数Predicate进行分区
    @Test
    public void testPartitionByPredicate() {
        List<String> list = Arrays.asList("KL1", "as", "kage", "springcloud", "C");
        Map<Boolean, List<String>> result = list.stream().collect(Collectors.partitioningBy(s -> s.length() > 2));
        System.out.println("result:" + result);
    }

    //使用Function （T->R）进行判断，分组
    @Test
    public void testGroupByFunction() {
        List<Student> students = Arrays.asList(new Student("jack", 19, 89.1), new Student("tom", 21, 60.5)
                , new Student("jerry", 19, 54.0), new Student("rick", 56, 99.1));
        Map<Integer, List<Student>> ageGroup = students.stream().collect(Collectors.groupingBy(Student::getAge));
        System.out.println(ageGroup);
    }

    //分组统计数量
    @Test
    public void testStatByGroup() {
        List<Student> students = Arrays.asList(new Student("jack", 19, 89.1), new Student("tom", 21, 60.5)
                , new Student("jerry", 19, 54.0), new Student("rick", 56, 99.1));
        Map<Integer, Long> collect = students.stream().collect(Collectors.groupingBy(Student::getAge, Collectors.counting()));
        System.out.println(collect);
    }

    //使用summarizing统计各种基本统计信息
    @Test
    public void testSummarizing() {
        List<Student> students = Arrays.asList(new Student("jack", 19, 89.1), new Student("tom", 21, 60.5)
                , new Student("jerry", 19, 54.0), new Student("rick", 56, 99.1));
        IntSummaryStatistics statistics = students.stream().collect(Collectors.summarizingInt(Student::getAge));
        System.out.println("平均数：" + statistics.getAverage());
        System.out.println("人数：" + statistics.getCount());
        System.out.println("最大值：" + statistics.getMax());
        System.out.println("最小值：" + statistics.getMin());
        System.out.println("总和：" + statistics.getSum());
    }

}
