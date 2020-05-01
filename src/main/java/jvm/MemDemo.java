package jvm;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Description:
 *
 * @author zhangr
 * 2020/4/22 0:12
 */
public class MemDemo {
    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000000000; i++) {
            Thread.sleep(1);
            list.add(UUID.randomUUID().toString());
        }
    }
}
