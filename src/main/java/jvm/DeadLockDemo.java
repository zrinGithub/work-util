package jvm;

/**
 * Description:
 *
 * @author zhangr
 * 2020/4/21 14:16
 */
public class DeadLockDemo {
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + "拿到lock1");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + "拿到lock2");
                }
            }
        },"A").start();
        new Thread(() -> {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "拿到lock2");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + "拿到lock1");
                }
            }
        },"B").start();
    }
}
