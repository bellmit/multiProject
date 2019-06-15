package messaging;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static messaging.Config.RABBITMQ_IP;

public class ConsumerRabbitMQ {
    private static final Logger ConsumerRabbitMQ_Logger = Logger.getLogger(ConsumerRabbitMQ.class.getName());
    //private List<IHandler> handlers = new ArrayList<>();

    public void runConsumer(final String topic) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(RABBITMQ_IP);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(topic, false, false, false, null);
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, StandardCharsets.UTF_8);
                    ConsumerRabbitMQ_Logger.log(Level.INFO, "[Live] Received ''{0}'' from {1}", new Object[]{message, topic});

                    //handleMessage(message);
                }
            };
            channel.basicConsume(topic, true, consumer);

        } catch (Exception ex) {
            ConsumerRabbitMQ_Logger.log(Level.SEVERE, "Exception: {0}", ex);
        }
    }

//    public void addHandlers(IHandler... handlers) {
//        this.handlers.addAll(Arrays.asList(handlers));
//    }
//
//    private void handleMessage(String message) {
//        boolean messageHandled = false;
//        for (IHandler handler : this.handlers) {
//            if (!messageHandled) {
//                messageHandled = handler.handleMessage(message);
//            }
//        }
//
//        if (!messageHandled) {
//            ConsumerRabbitMQ_Logger.severe("Message could not be handled by any handler");
//        }
//    }
}
