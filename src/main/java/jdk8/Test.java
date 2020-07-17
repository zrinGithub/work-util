package jdk8;

import org.apache.commons.lang3.StringUtils;

/**
 * Description:
 *
 * @author zhangr
 * 2020/7/17 11:11
 */
public class Test {
    public static void main(String[] args) {
        String fileBase64 = "data:image/pds;base64,asasas";
        String between = StringUtils.substringBetween(fileBase64, "data:image/", ";base64,");
        String after = StringUtils.substringAfter(fileBase64, "base64,");
        System.out.println(between);
        System.out.println(after);
    }
}
