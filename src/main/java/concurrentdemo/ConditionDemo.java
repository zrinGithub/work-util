package concurrentdemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @author zhangr
 * 2020/5/11 15:09
 * }
 */
public class ConditionDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        Thread A = new Thread(()->{
            lock.lock();
            try{
                System.out.println("start await1");
                condition1.await();
                System.out.println("end await1");
                Thread.sleep(2000);
                System.out.println("start signal2");
                condition2.signal();
                System.out.println("end signal2");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });


        Thread B = new Thread(()->{
            lock.lock();
            try{
                System.out.println("start signal");
                condition1.signal();
                System.out.println("end signal");
                System.out.println("start await2");
                condition2.await();
                System.out.println("end await2");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });

        A.start();
        B.start();
    }
}
