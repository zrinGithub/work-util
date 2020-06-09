package concurrentdemo;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @author zhangr
 * 2020/5/26 9:20
 * }
 */
public class CycleBarrierDemo {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
        System.out.println(Thread.currentThread().getName() + " reach barrier!");
    });

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(()->{
            try {
                System.out.println(Thread.currentThread().getName() + " start run");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + " you can continue......");
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        //线程2等待一段时间后再达到barrier
        executorService.submit(()->{
            try {
                System.out.println(Thread.currentThread().getName() + " start run");
                Thread.sleep(5000);
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + " you can continue......");
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        //等待当前线程池中的线程执行完成
        executorService.shutdown();
    }
}
