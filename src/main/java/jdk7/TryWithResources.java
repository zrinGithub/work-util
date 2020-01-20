package jdk7;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/20 15:52
 * }
 */
public class TryWithResources {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "test.txt";
        OutputStream fileOutputStream = new FileOutputStream(path);
        //老的做法
        try {
            fileOutputStream.write("test".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //jdk7以后，使用try-with-resources
        //里面可以放置多个
        //越早声明的对象，越晚被关闭
        try (OutputStream outputStream = new FileOutputStream(path); OutputStream outputStream2 = new FileOutputStream(path)) {
            outputStream.write("电话交换机".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
