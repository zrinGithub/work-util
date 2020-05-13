package concurrentdemo;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * Description:
 *
 * @author zhangr
 * 2020/5/11 10:01
 * }
 */
public class FIFOMutex {
    private final AtomicBoolean locked = new AtomicBoolean(false);
    private final Queue<Thread> waiters = new ConcurrentLinkedQueue<>();

    public void lock() {
        boolean wasInterrupted = false;
        Thread currentThread = Thread.currentThread();
        waiters.add(currentThread);

        //判断是否队首
        while (waiters.peek() != currentThread || !locked.compareAndSet(false, true)) {
            LockSupport.park(this);
            if (Thread.interrupted())
                wasInterrupted = true;
        }

        waiters.remove();
        if (wasInterrupted)
            currentThread.interrupt();
    }

    public void unlock() {
        locked.set(false);
        //释放队首元素
        LockSupport.unpark(waiters.peek());
    }
}
