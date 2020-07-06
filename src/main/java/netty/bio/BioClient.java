package netty.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Description:
 *
 * @author zhangr
 * 2020/7/1 10:37
 */
public class BioClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             //获取返回的时间
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             //发送数据给服务端
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.println("this is the client!");
            //获取服务器返回的信息
            String responseMsg = in.readLine();
            System.out.println("get server time :" + responseMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
