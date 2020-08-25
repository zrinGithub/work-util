package leetcode;

/**
 * Description:
 *
 * @author zhangr
 * 2020/8/13 10:27
 */
interface A {
    static void et() {
    }

    default int test() {
        return 1;
    }
}

interface B {
    default int test() {
        return 0;
    }
}

public class Test implements A, B {
    @Override
    public int test() {
        A.super.test();
        B.super.test();
        return 3;
    }

    public static void main(String[] args) {
        new Test().test();
    }
}
