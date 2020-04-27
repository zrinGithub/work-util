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
public class BufferDemo {

    public static void main(String[] args) {
        try (RandomAccessFile accessFile = new RandomAccessFile("D:\\code\\util\\work-util\\src\\main\\resources\\test1.txt", "rw")) {
            //获取输入通道
            FileChannel inChannel = accessFile.getChannel();
            //开辟空间，参数：The new buffer's capacity, in bytes
            //这里每次取10个字节，也就是每次10*8bit，对应10个ascii码，也就是10个字符
            ByteBuffer buffer = ByteBuffer.allocate(10);

            int bytesRead = inChannel.read(buffer);
            while (bytesRead != -1) {
                System.out.println("read :" + bytesRead);
                //flip是为了后面读取数据
                buffer.flip();
                //这里只取2个字符
                System.out.println("get: " + (char) buffer.get(2));

                //1.清空缓存数据
                buffer.clear();
                System.out.println("remain: " + (char) buffer.get());
                //2.仅仅清空已经读取的数据
//                buffer.compact();
//                System.out.println("remain: " + (char) buffer.get());
                //Channel->Buffer
                bytesRead = inChannel.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}