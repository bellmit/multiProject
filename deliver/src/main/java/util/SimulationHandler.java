package util;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimulationHandler {

    private static final Logger LOGGER = Logger.getLogger(SimulationHandler.class.getName());
    private static final String HOST = "192.168.24.110";

    public void startSimulation(List<String> coords, List<String> oderIds,String currentid) {
        String user = "root";
        String ssh = "root";
        String command = " python3 /home/python/simulation.py";
        try {
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, HOST, 22);
            session.setPassword(ssh);
            session.setConfig(config);
            session.connect();
            LOGGER.log(Level.INFO, "Connected");

            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            channel.setErrStream(System.err);
            channel.connect();
            TimeUnit.SECONDS.sleep(5);
            SimulationMessageSender simulationMessageSender = new SimulationMessageSender();
            simulationMessageSender.sendCoords(coords.get(0), HOST);
            SimulationMessageReceiver simulationMessageReceiver = new SimulationMessageReceiver();
            simulationMessageReceiver.receiveCoords(coords, oderIds, HOST, currentid);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage() + " ssh");
        }

    }
}


