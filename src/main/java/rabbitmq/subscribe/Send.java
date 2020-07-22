package rabbitmq.subscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Description:
 *
 * @author zhangr
 * 2020/7/16 11:25
 */
public class Send {
    private final static String EXCHANGE_NAME = "logs";

    private void send() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            String msg = "Hello World!\t" + RandomStringUtils.randomAlphabetic(10);
            channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
            System.out.println("Send : " + msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Send().send();
    }
}
