package util;

import event.SimulationEvent;
import socket.SimulationSocket;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SimulationMessageFormater {

    private static final Logger LOGGER = Logger.getLogger(SimulationHandler.class.getName());


    public static void FormatMessage(SimulationEvent simulationEvent , String message , SimulationSocket socket){
        String[] splitMessage = message.split(",");
        String msg1 =  splitMessage[2].replace("[","");
        String msg2 = splitMessage[3].replace("]","");
        simulationEvent.setLat(msg1);
        simulationEvent.setLon(msg2);
        socket.sendUpdateSimulation(simulationEvent);
        LOGGER.log(Level.INFO, " [x] Received ' Lat: " + msg1 + "Lon: "+msg2+"'" +message);
        LOGGER.log(Level.INFO, " [x] Done");
    }
}
