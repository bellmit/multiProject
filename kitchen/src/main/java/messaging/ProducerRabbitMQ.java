package messaging;

import com.rabbitmq.client.Channel;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class ProducerRabbitMQ {
    private static final Logger ProducerRabbitMQ_Logger = Logger.getLogger(ProducerRabbitMQ.class.getName());

    @Inject
    ConnectionFactoryProvider cfp;

    public void sendMsg (String message, String queueName){
        try {
            Channel channel = cfp.getChannel();
            channel.queueDeclare(queueName, true, false, false, null);

            channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));

            ProducerRabbitMQ_Logger.log(Level.INFO, " [Live] Sent ''{0}''", message);
        } catch (Exception ex) {
            ProducerRabbitMQ_Logger.log(Level.SEVERE, ex.toString());
        }
    }
}
