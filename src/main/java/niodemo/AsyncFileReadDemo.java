package niodemo;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * Description:
 *
 * @author zhangr
 * 2020/5/14 9:50
 * }
 */
public class AsyncFileReadDemo {
    public static void main(String[] args) throws Exception {
        Path path = Paths.get("D:\\code\\util\\work-util\\src\\main\\java\\niodemo\\DeleteDirectoryRecursively.java");

        AsynchronousFileChannel fileChannel =
                AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        //buffer开辟空间
        ByteBuffer buffer = ByteBuffer.allocate(11);
        //指定从文件开始的位置读取数据
        long position = 3;

        //异步读取文件
        Future<Integer> operation = fileChannel.read(buffer, position);

        //在完成读取之前等待
        while (!operation.isDone()) ;

        //buffer切换为读模式
        buffer.flip();

        //输出
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        System.out.println(new String(data));
        buffer.clear();
    }
}
