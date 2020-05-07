package concurrentdemo;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Description:
 *
 * @author zhangr
 * 2020/5/7 9:12
 * }
 */
public class TestUnSafe {
    static Unsafe unSafe;

    static Long stateOffset;

    private volatile long state = 0;

    static {
        try {
            final Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            //因为是静态变量，直接取就可以
            unSafe = (Unsafe) field.get(null);
            //获取state变量在类TestUnSafe中的偏移位置
            stateOffset = unSafe.objectFieldOffset(TestUnSafe.class.getDeclaredField("state"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestUnSafe test = new TestUnSafe();
        boolean result = unSafe.compareAndSwapLong(test, stateOffset, 0, 1);
        System.out.println(result);
    }
}
