package messaging;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

import static messaging.RabbitMQConfig.RABBITMQ_IP;

public class ConnectionFactoryProvider {
    private Channel channel;
    private static final Logger ConnectionFactoryProvider_Logger = Logger.getLogger(ConnectionFactoryProvider.class.getName());

    public ConnectionFactoryProvider() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ_IP);

        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (Exception ex) {
            ConnectionFactoryProvider_Logger.log(Level.SEVERE, ex.toString());
        }

        ConnectionFactoryProvider_Logger.log(Level.INFO, "ConnectionFactoryProvider container created");
    }


    public Channel getChannel(){
        return this.channel;
    }
}
