package util;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import messaging.RabbitMQConfig;
import messaging.SimulationMessageReceiver;
import messaging.SimulationMessageSender;
import ssh.SSHSender;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimulationHandler {

    private static final Logger LOGGER = Logger.getLogger(SimulationHandler.class.getName());

    public void startSimulation(List<String> coords, List<String> oderIds,String currentid, String deliverId) {

        try {
            SSHSender.sendSSHCommand();
            SimulationMessageSender simulationMessageSender = new SimulationMessageSender();
            simulationMessageSender.sendCoords(coords.get(0), RabbitMQConfig.RABBITMQ_IP);
            SimulationMessageReceiver simulationMessageReceiver = new SimulationMessageReceiver();
            simulationMessageReceiver.receiveCoords(coords, oderIds, currentid,deliverId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage() + " ssh");
        }

    }
}


