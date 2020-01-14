package jvm;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/13 17:25
 * }
 */
public class StringDemo {
    public static void main(String[] args) {
        String a = "abc";
        String b = "abc";
        System.out.println(a==b);
        String c = new String("abc");
        System.out.println(a==c);
        System.out.println(a==c.intern());

        String d = new String("pack");
        System.out.println(d.intern()=="pack");
    }
}
