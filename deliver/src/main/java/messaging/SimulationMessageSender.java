package messaging;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import messaging.RabbitMQConfig;
import util.SimulationHandler;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimulationMessageSender {

    private static final Logger LOGGER = Logger.getLogger(SimulationHandler.class.getName());

    @Inject
    ConnectionFactoryProvider cfp;

    public SimulationMessageSender() {
    }

    public void sendCoords(String coords) {
        try {
            Channel channel = cfp.getChannel();
            channel.queueDeclare(RabbitMQConfig.SENDER_QUEUE, true, false, false, null);

            channel.basicPublish("", RabbitMQConfig.SENDER_QUEUE,
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    coords.getBytes(StandardCharsets.UTF_8));
            LOGGER.log(Level.INFO, " [x] Sent '" + coords + "'");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage() + " rabbit");
        }
    }

    public void sendStatusUpdate(String message, String queueName) {
        try {
            Channel channel = cfp.getChannel();
            channel.queueDeclare(queueName, true, false, false, null);

            channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));

            LOGGER.log(Level.INFO, " [Live] Sent ''{0}''", message);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.toString());
        }
    }

}
