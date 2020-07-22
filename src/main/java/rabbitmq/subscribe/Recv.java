package rabbitmq.subscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * Description:
 *
 * @author zhangr
 * 2020/7/16 14:43
 */
public class Recv {
    private final static String EXCHANGE_NAME = "logs";

    private void receive() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, EXCHANGE_NAME, "");

            System.out.println("Waiting for the message");

            DeliverCallback deliverCallback = (consumerTag, message) -> {
                String msg = new String(message.getBody(), "UTF-8");
                System.out.println("Receive msg : " + msg);
            };

            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Recv().receive();
    }
}
