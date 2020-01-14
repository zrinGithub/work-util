package jdk8;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/14 9:33
 * }
 */

interface A{
    default void eat(){
        System.out.println("A.eat()");
    }
    static void sleep(){
        System.out.println("A.sleep()");
    }
    void run();
    void seek();
}

public class InterfaceDemo implements A {
    @Override
    public void run() {
        System.out.println("Sub.run()");
    }

    @Override
    public void seek() {
        System.out.println("Sub.seek()");
    }

    public static void main(String[] args) {
        InterfaceDemo interfaceDemo = new InterfaceDemo();
        interfaceDemo.run();
        interfaceDemo.seek();
        interfaceDemo.eat();
        A.sleep();

    }
}
