package concurrentdemo;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @author zhangr
 * 2020/5/26 9:40
 * }
 */
public class CycleBarrierDemo2 {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "reach step 1.");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "reach step 2.");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "reach step 3.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "reach step 1.");
                Thread.sleep(3000);
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "reach step 2.");
                Thread.sleep(5000);
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "reach step 3.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //等待已有线程执行完毕后关闭线程池
        executorService.shutdown();
    }
}
