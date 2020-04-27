package niodemo;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Description:
 *
 * @author zhangr
 * 2020/4/27 10:29
 * }
 */
public class ChannelDemo {

    public static void main(String[] args) {
        try (RandomAccessFile accessFile = new RandomAccessFile("D:\\code\\util\\work-util\\src\\main\\resources\\test1.txt", "rw")) {
            //获取输入通道
            FileChannel inChannel = accessFile.getChannel();
            //开辟空间，参数：The new buffer's capacity, in bytes
            //这里每次读取一个字节
            ByteBuffer buffer = ByteBuffer.allocate(1);

            int bytesRead = inChannel.read(buffer);
            while (bytesRead != -1) {
                System.out.println("read :" + bytesRead);
                //flip是为了后面读取数据
                buffer.flip();
                //缓存中还有数据的时候继续get
                while (buffer.hasRemaining())
                    System.out.println((char) buffer.get());
                //清空缓存数据
                buffer.clear();
                //Channel->Buffer
                bytesRead = inChannel.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}