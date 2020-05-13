package concurrentdemo;

import java.util.concurrent.locks.LockSupport;

/**
 * Description:
 *
 * @author zhangr
 * 2020/5/11 9:06
 * }
 */
public class LockSupportDemo {
    public static void main(String[] args) throws Exception {
        Thread A = new Thread(() -> {
            System.out.println("child");
            while (!Thread.currentThread().isInterrupted()){
                LockSupport.park();
            }
            System.out.println("child thread unpark");
        });
        A.start();
        Thread.sleep(1000);

        System.out.println("main thread begin unpark!");

        //中断子线程
        A.interrupt();
        //不会起作用，因为循环判定失败
//        LockSupport.unpark(A);
    }
}
