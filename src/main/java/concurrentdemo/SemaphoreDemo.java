package concurrentdemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Description:
 *
 * @author zhangr
 * 2020/5/26 10:14
 * }
 */
public class SemaphoreDemo {
    private static Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName() + " over");
            semaphore.release();
        });
        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName() + " over");
            semaphore.release();
        });
        semaphore.acquire(2);
        System.out.println("all child thread over!");

        executorService.shutdown();
    }
}
