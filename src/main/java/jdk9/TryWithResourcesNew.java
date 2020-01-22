package jdk9;

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
public class TryWithResourcesNew {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "test.txt";
        OutputStream outputStream = new FileOutputStream(path);
        OutputStream outputStream2 = new FileOutputStream(path);
        try (outputStream; outputStream2) {
            outputStream.write("电话交换机".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
