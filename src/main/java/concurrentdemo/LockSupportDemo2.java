package concurrentdemo;

import java.util.concurrent.locks.LockSupport;

/**
 * Description:
 *
 * @author zhangr
 * 2020/5/11 9:06
 * }
 */
public class LockSupportDemo2 {
    public static void main(String[] args) throws Exception {
        LockSupport.unpark(Thread.currentThread());
        LockSupport.park();
        System.out.println("end park1!");
        LockSupport.park();
        System.out.println("end park2!");
    }
}
/**
 * end park1!
 * 阻塞 java.lang.Thread.State: WAITING
 */
