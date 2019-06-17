package messaging;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimulationMessageCleanUp {

    private static final Logger LOGGER = Logger.getLogger(SimulationMessageCleanUp.class.getName());

    public static void clean(Channel channel, String finalcoord, Connection connection){
        try {
            channel.queueDelete("simulation_queue" + finalcoord);
            channel.queueDelete("deadletter" + finalcoord);
            connection.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,"Something went wrong in cleanup: "+ e.getMessage());
        }

    }
    }

