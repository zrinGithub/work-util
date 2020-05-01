package jvm;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/27 20:51
 */
public class OOMErrorTest {
    //VM options:
    //-Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\zr\tmp
    public static void main(String[] args) {
        String name = "test";
        for (int i = 0; i < 1000000000; i++) {
            name += name;
        }
        System.out.println(name);
    }
}
