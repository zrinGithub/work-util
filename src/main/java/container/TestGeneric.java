package container;

import java.util.Arrays;
import java.util.Collection;

/**
 * Description:
 *
 * @author zhangr
 * 2020/5/21 10:12
 * }
 */
class Parent {
}

class Base extends Parent {
}

class Sub extends Base {
}

class Acceptor<E> {
    public void sExtends(Collection<? extends E> c) {
    }

    public void sSuper(Collection<? super E> c) {
    }
}

public class TestGeneric {
    public static void main(String[] args) {
        final Acceptor<Base> acceptor = new Acceptor<>();
        //基类都可以满足
        acceptor.sExtends(Arrays.asList(new Base()));
        acceptor.sSuper(Arrays.asList(new Base()));
        //<? extends E>只能适用子类
        acceptor.sExtends(Arrays.asList(new Sub()));
//        acceptor.sExtends(Arrays.asList(new Parent()));
        //<? super E>可以使用所用相关
        acceptor.sSuper(Arrays.asList(new Sub()));
        acceptor.sSuper(Arrays.asList(new Parent()));
    }

}
