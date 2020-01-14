package jdk8;

import java.io.UnsupportedEncodingException;
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
    public static void main(String[] args) throws UnsupportedEncodingException {
        Base64.Encoder encoder = Base64.getEncoder();
        Base64.Decoder decoder = Base64.getDecoder();

        String encode = encoder.encodeToString("战争".getBytes(StandardCharsets.UTF_8));
        System.out.println(encode);

        String decode = new String(decoder.decode(encode), StandardCharsets.UTF_8);
        System.out.println(decode);
    }
}
