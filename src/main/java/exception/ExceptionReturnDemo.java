package exception;

/**
 * Description:
 *
 * @author zhangr
 * 2020/4/30 10:02
 * }
 */
public class ExceptionReturnDemo {
    public static void main(String[] args) {
        System.out.println("test1():" + test1());
        System.out.println("test2():" + test2());
    }

    public static int test1() {
        int a = 1;
        try {
            System.out.println(a / 0);
            a = 2;
        } catch (ArithmeticException e) {
            a = 3;
            return a;
        } finally {
            a = 4;
        }
        return a;
    }

    public static int test2() {
        int a = 1;
        try {
            System.out.println(a / 0);
            a = 2;
        } catch (ArithmeticException e) {
            a = 3;
            return a;
        } finally {
            a = 4;
            return a;
        }
    }
}
/**
 * main() output:
 * test1():3
 * test2():4
 */
