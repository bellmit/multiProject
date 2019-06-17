package util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class SimulationMessageCleanUp {

    public static void clean(Channel channel, String finalcoord, Connection connection){
        try {
            channel.queueDelete("simulation_queue" + finalcoord);
            channel.queueDelete("deadletter" + finalcoord);
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    }

