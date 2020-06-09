package niodemo;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Description:
 *
 * @author zhangr
 * 2020/5/13 16:40
 * }
 */
public class FilesDemo {
    public static void main(String[] args) {
        Path path1 = Paths.get(".\\A");
        Path path2 = Paths.get(".\\src");
        boolean exists1 = Files.exists(path1, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
        boolean exists2 = Files.exists(path2, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
        System.out.println(exists1);
        System.out.println(exists2);

    }
}
