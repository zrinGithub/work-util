package jdk8;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/14 9:50
 * }
 */
@Data
@AllArgsConstructor
class Student {
    private int age;
}

public class OptionalDemo {
    public static void main(String[] args) {
        Student a = null;
        Student student = new Student(11);
        Student result = Optional.ofNullable(a).orElse(student);
        Optional<Student> optional = Optional.ofNullable(a);
        System.out.println("optional.isPresent():" + optional.isPresent());

        //NoSuchElementException
//        System.out.println("optional.get(" + optional.get());

        //结合lambda
        //map中的参数Function<T, R>实现   T-R
        Integer orElse = Optional.ofNullable(a).map(Student::getAge).orElse(45);
        System.out.println(orElse);
    }
}
