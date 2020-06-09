package niodemo;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Description:
 *
 * @author zhangr
 * 2020/5/14 9:21
 * }
 */
public class DeleteDirectoryRecursively {
    public static void main(String[] args) {
        Path rootPath = Paths.get("D:\\tmp\\a");

        try {
            Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
                //实现visitFile方法，访问到一个文件就删除
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("delete file: " + file.toString());
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                //实现postVisitDirectory，访问文件夹完成后就删除
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    System.out.println("delete dir: " + dir.toString());
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
