package file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileOperation {
    public static boolean contentEqual(File file1, File file2,String charset) throws IOException {
        boolean check = false;
        check = StringUtils.isBlank(charset)? FileUtils.contentEquals(file1,file2):FileUtils.contentEqualsIgnoreEOL(file1,file2,charset);
        return check;
    }

}
