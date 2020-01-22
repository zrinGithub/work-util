package jdk9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/22 16:43
 */
public class ReadOnlyList {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        //旧的做法
        //设置为只读集合,set\map也可以这么操作
        List<String> unmodifiableList = Collections.unmodifiableList(list);
        System.out.println(unmodifiableList);
        //UnsupportedOperationException
//        unmodifiableList.add("a");


        //jdk9 新的做法
        List<String> strings = List.of("a", "b");
        System.out.println(strings);
        //UnsupportedOperationException
//        strings.add("s");
    }
}
