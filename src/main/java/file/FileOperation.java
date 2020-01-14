package file;

import lombok.Data;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class FileOperation {
    public void file2Class(String srcFile, String des, String charset) throws IOException {
        File file = new File(srcFile);
        //readLine
//        List<String> stringList = FileUtils.readLines(file, charset);
        String fileStr = FileUtils.readFileToString(file, charset);
        String[] strings = fileStr.split("=================================");
        File desDir = new File(des);
        if (!desDir.exists())
            //这是创建文件夹desDir.createNewFile();
            desDir.mkdirs();
        //创建文件
//

        for (String s : strings) {
            if (StringUtils.isBlank(s)) {
                continue;
            }
            s = s.replaceAll("package;", "");
            s = s.replaceAll("public class", "import lombok.Data;\n\n\n@Data\npublic class");
            s = s.substring(0, s.indexOf("public", s.indexOf("public") + 1));
            s += "\n}";
            System.out.println(s);

        }

    }

    public static void main(String[] args) {
        try {
//            new FileOperation().file2Class("src/main/resources/result.txt", "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

