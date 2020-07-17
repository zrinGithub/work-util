package rabbitmq.simpleoneway;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 *
 * @author zhangr
 * 2020/7/16 11:25
 */
public class Send {
    //name the queue
    //it will only be created if it doesn't exist already
    private final static String QUEUE_NAME = "hello";

    private void send() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        //create a connection to the server
        try (Connection connection = factory.newConnection();   //abstract a socket connection
             Channel channel = connection.createChannel()) {
            /**
             * param:
             * queueName
             * durable
             * exclusive
             * autoDelete
             * arguments
             */
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String msg = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            System.out.println("Send : " + msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Send().send();
    }
}
