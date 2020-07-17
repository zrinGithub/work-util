package jdk8;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/14 9:39
 * }
 */
public class Base64Demo {
    public static void main(String[] args) throws IOException {
        Base64.Encoder encoder = Base64.getEncoder();
        Base64.Decoder decoder = Base64.getDecoder();
        File file = new File("C:\\Users\\Lenovo\\Pictures\\elk\\elk.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fileInputStream.read(bytes);
        fileInputStream.close();
        String baseFile = encoder.encodeToString(bytes);
        System.out.println(baseFile);

        byte[] decode1 = decoder.decode(baseFile.getBytes());
        FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\Lenovo\\Pictures\\elk\\elk2.jpg"));
        fos.write(decode1);
        fos.close();


        String encode = encoder.encodeToString("战争".getBytes(StandardCharsets.UTF_8));
        System.out.println(encode);

        String decode = new String(decoder.decode(encode), StandardCharsets.UTF_8);
        System.out.println(decode);
    }
}
