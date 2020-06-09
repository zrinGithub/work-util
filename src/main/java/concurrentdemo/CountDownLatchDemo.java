package concurrentdemo;

import java.util.concurrent.CountDownLatch;

/**
 * Description:
 *
 * @author zhangr
 * 2020/5/25 16:30
 * }
 */
public class CountDownLatchDemo {
    private static volatile CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws Exception {
        System.out.println(System.currentTimeMillis());
        new Thread(() -> countDownLatch.countDown()).start();
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }).start();
        countDownLatch.await();
        System.out.println("all over end=" + System.currentTimeMillis());
    }
}
