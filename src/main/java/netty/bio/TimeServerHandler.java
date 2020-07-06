package netty.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * Description:
 *
 * @author zhangr
 * 2020/7/1 10:03
 */
public class TimeServerHandler implements Runnable {
    private final Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("=========TimeHandler run()=========");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
             PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             socket) {

            String body = null;
            while ((body = in.readLine()) != null && body.length() != 0) {
                System.out.println("the time server receive msg: " + body);
                out.println(new Date().toString());
                //前面设定了输出自动刷新，这里不用刷入
//                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
