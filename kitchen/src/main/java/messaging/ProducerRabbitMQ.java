package messaging;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

import static messaging.Config.RABBITMQ_IP;

@Stateless
public class ProducerRabbitMQ {
    private static final Logger ProducerRabbitMQ_Logger = Logger.getLogger(ProducerRabbitMQ.class.getName());

    public void sendMsg (String message, String topic){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ_IP);
        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.queueDeclare(topic, false, false, false, null);

            channel.basicPublish("", topic, null, message.getBytes(StandardCharsets.UTF_8));

            ProducerRabbitMQ_Logger.log(Level.INFO, " [Live] Sent ''{0}''", message);
        } catch (Exception ex) {
            ProducerRabbitMQ_Logger.log(Level.SEVERE, ex.toString());
        }
    }
}
