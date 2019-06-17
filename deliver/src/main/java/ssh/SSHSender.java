package ssh;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import service.LogService;

import javax.inject.Inject;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SSHSender {
    @Inject
    LogService logService;
    private static final Logger LOGGER = Logger.getLogger(SSHSender.class.getName());


    public static void sendSSHCommand() {
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession("root", "192.168.24.110", 22);
            session.setPassword("root");
            session.setConfig(config);
            session.connect();
        } catch (JSchException e) {
            LOGGER.log(Level.SEVERE, "Connection with ssh refused");
        } catch(NullPointerException e){
            LOGGER.log(Level.SEVERE, "session is null");
        }
        LOGGER.log(Level.INFO, "Connected");

        ChannelExec channel = null;
        try {
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand("python3 /home/python/simulation.py");
            channel.setErrStream(System.err);
            channel.connect();
        } catch (JSchException e) {
            LOGGER.log(Level.SEVERE, "Something went wrong when trying to open channel: " + e.getMessage());
        } catch (NullPointerException e){
            LOGGER.log(Level.SEVERE, "Open channel is null");
        }

    }
}
