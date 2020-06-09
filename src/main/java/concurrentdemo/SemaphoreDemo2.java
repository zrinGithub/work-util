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
public class SemaphoreDemo2 {
    private static Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(()->{
            try {
                semaphore.acquire(1);
                System.out.println(Thread.currentThread().getName() + " get semaphore");
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        executorService.submit(()->{
            try {
                semaphore.acquire(1);
                System.out.println(Thread.currentThread().getName() + " get semaphore");
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        executorService.submit(()->{
            try {
                semaphore.acquire(1);
                System.out.println(Thread.currentThread().getName() + " get semaphore");
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        //再次产生1个信号量
        Thread.sleep(2000);
        System.out.println("main thread release 1 semaphore");
        semaphore.release(1);
        System.out.println("all child thread over!");

        executorService.shutdown();
    }
}
