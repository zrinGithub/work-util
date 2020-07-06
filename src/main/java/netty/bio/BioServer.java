package netty.bio;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description:
 *
 * @author zhangr
 * 2020/7/1 9:59
 */
public class BioServer {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("--------------Server start--------------");
            while (true) {
                //等待连接
                Socket socket = serverSocket.accept();
                //调用Handler线程来处理连接
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
