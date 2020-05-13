package concurrentdemo;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Description:
 *
 * @author zhangr
 * 2020/5/8 9:22
 * }
 */
public class AtomicTest {
    //创建Long类型的原子计数器
    private static AtomicLong atomicLong = new AtomicLong();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            int count = 10000;
            while (count-- > 0) {
                System.out.println(Thread.currentThread().getName() + " now value:" + atomicLong.incrementAndGet());
            }
        }, "A");
        Thread threadB = new Thread(() -> {
            int count = 10000;
            while (count-- > 0) {
                System.out.println(Thread.currentThread().getName() + " now value:" + atomicLong.addAndGet(3));
            }
        }, "B");
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        System.out.println("main over , now atomicLong = " + atomicLong.get());
    }
}
/**
 * output:
 * ......
 * A now value:392
 * B now value:395
 * A now value:396
 * A now value:397
 * A now value:398
 * A now value:399
 * A now value:400
 * main over , now atomicLong = 400
 */
