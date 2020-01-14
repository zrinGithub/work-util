package jdk8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/14 9:44
 * }
 */
public class LocalDateTimeDemo {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        //日期转换
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDate = now.format(formatter);
        System.out.println(formatDate);

        LocalDateTime parse = LocalDateTime.parse(formatDate, formatter);
        System.out.println(parse);

    }
}
