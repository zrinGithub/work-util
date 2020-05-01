package file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author zhangr
 * 2020/5/1 16:47
 */
public class FileRawOperation {
    public static void main(String[] args) {

        //找出某目录下的所有子目录以及子文件并打印到控制台上
        List<String> paths = new ArrayList<>();

        getAllFilePaths(new File("/Users/xdclass/Desktop/小滴课堂-架构面试题教程/demo"), paths);

        for (String path : paths) {
            System.out.println(path);
        }


    }

    private static void getAllFilePaths(File filePath, List<String> paths) {
        File[] files = filePath.listFiles();
        if (files == null) {
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                paths.add(f.getPath());
                getAllFilePaths(f, paths);
            } else {
                paths.add(f.getPath());
            }
        }
    }
}
