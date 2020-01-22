package jdk9;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/22 16:17
 */
class B extends A {

}

class A implements PrivateMethodDemo {
    public static void main(String[] args) {
        PrivateMethodDemo a = new A();
        a.test1();
        A.test4();
        //类的静态方法被继承了
        B.test4();
        //接口的静态方法不能被继承
//        A.test3();
        //类似的例子
        List<String> strings = List.of("a", "c");
//        ArrayList.of
    }

    public static void test4() {
        System.out.println("A");
    }
}

public interface PrivateMethodDemo {
    public static void test3() {
        System.out.println("PrivateMethodDemo");
    }

    default void test1() {
        test();
    }

    private void test() {
        System.out.println("1");
    }
}
