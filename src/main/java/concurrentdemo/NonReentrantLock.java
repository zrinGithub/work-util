package concurrentdemo;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Description:
 *
 * @author zhangr
 * 2020/5/15 10:13
 * }
 */
public class NonReentrantLock implements Lock, Serializable {
    //内部工具类，用于维护AQS队列数据
    private static class Sync extends AbstractQueuedSynchronizer {
        //state=0的时候，尝试获取锁
        @Override
        protected boolean tryAcquire(int arg) {
            assert arg == 1;
            if (compareAndSetState(0, 1)) {
                //设置锁的拥有者为当前线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        //尝试释放锁，设置state=0
        @Override
        protected boolean tryRelease(int arg) {
            assert arg == 1;
            //如果当前的状态是0，表示没有占有锁
            //尝试释放锁抛出异常
            if (getState() == 0)
                throw new IllegalMonitorStateException();
            //解除当前线程对锁的占有
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        //锁是否已经被持有
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        //提供条件变量
        public Condition newCondition() {
            return new ConditionObject();
        }
    }

    //创建AQS变量操作类Sync
    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
