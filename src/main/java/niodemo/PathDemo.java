package niodemo;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Description:
 *
 * @author zhangr
 * 2020/5/12 17:24
 * }
 */
public class PathDemo {
    public static void main(String[] args) {
        Path path = Paths.get(".");
        System.out.println(path.normalize().toAbsolutePath());
    }
}
