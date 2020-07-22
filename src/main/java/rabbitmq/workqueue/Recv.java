package rabbitmq.workqueue;

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
    //name the queue
    //it will only be created if it doesn't exist already
    private final static String QUEUE_NAME = "hello";

    private void receive() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);

        try {
            //create a connection to the server
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            //declare a queue here, because we may start a consume before the publisher
            //make sure the queue exits before we try to consume message from it.
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println("Waiting for the message");

            //accept only one unack-ed message at a time
            //param is prefetch
            channel.basicQos(2);

            //prepare the callback: when get message, print on the console
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                String msg = new String(message.getBody(), "UTF-8");
                System.out.println("Receive msg : " + msg);
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("done work with msg");
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                }
            };

            //start receive message
            channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Recv().receive();
    }
}
