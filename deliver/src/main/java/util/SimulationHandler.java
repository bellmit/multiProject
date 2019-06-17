package util;

import messaging.SimulationMessageReceiver;
import messaging.SimulationMessageSender;
import ssh.SshSender;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimulationHandler {

    private static final Logger LOGGER = Logger.getLogger(SimulationHandler.class.getName());

    public void startSimulation(List<String> coords, List<String> oderIds,String currentid, String deliverId) {
        try {
            SshSender.sendSSHCommand();
            TimeUnit.SECONDS.sleep(5);
            SimulationMessageSender simulationMessageSender = new SimulationMessageSender();
            simulationMessageSender.sendCoords(coords.get(0));
            SimulationMessageReceiver simulationMessageReceiver = new SimulationMessageReceiver();
            simulationMessageReceiver.receiveCoords(coords, oderIds, currentid,deliverId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Sleep fail: " + e.getMessage());
        }

    }
}


