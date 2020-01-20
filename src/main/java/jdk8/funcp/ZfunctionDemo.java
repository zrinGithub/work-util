package jdk8.funcp;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/14 10:29
 * }
 */
public class ZfunctionDemo {
    public static void main(String[] args) {
        String s = useZ(1, 12.3, (i, j) -> i + j + "");
        System.out.println(s);
    }

    public static String useZ(Integer i, Double j, ZFunction<Integer, Double, String> fun) {
        return fun.f(i, j);
    }
}
