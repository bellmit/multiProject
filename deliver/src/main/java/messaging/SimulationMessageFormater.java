package messaging;

import com.rabbitmq.client.Channel;
import event.SimulationEvent;
import socket.SimulationSocket;
import util.SimulationHandler;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimulationMessageFormater {

    private static final Logger LOGGER = Logger.getLogger(SimulationHandler.class.getName());


    public static void FormatMessage(SimulationEvent simulationEvent , String message , SimulationSocket socket) {
        String[] splitMessage = message.split(",");
        if (splitMessage.length > 1) {
            String msg1 = splitMessage[2].replace("[", "");
            String msg2 = splitMessage[3].replace("]", "");
            simulationEvent.setLat(msg1);
            simulationEvent.setLon(msg2);
            socket.sendUpdateSimulation(simulationEvent);
            LOGGER.log(Level.INFO, " [x] Received ' Lat: " + msg1 + "Lon: " + msg2 + "'" + message);
            LOGGER.log(Level.INFO, " [x] Done");
        }
    }
    public static void FormatClose(SimulationEvent simulationEvent, String currentid, SimulationSocket socket ){
            simulationEvent.setLat("stop" + currentid);
            simulationEvent.setLon("stop" + currentid);
            List<String> ids = simulationEvent.getOrderid();
            socket.sendUpdateSimulation(simulationEvent);
            ids.remove(currentid);
            simulationEvent.setOrderid(ids);
            LOGGER.log(Level.INFO, "Stopped");
           }
    }

