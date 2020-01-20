package jdk8.funcp;

/**
 * Description:
 * 函数式接口：只能有一个待实现的方法
 *
 * @author zhangr
 * 2020/1/14 10:26
 * }
 */
@FunctionalInterface
public interface ZFunction<T, U, R> {
    R f(T t, U u);

    default void test() {
        System.out.println("可以包含默认方法");
    }

    static void test2() {
        System.out.println("可以实现类方法");
    }
}