package ssh;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SshSender {
    private static final Logger LOGGER = Logger.getLogger(SshSender.class.getName());


    public static void sendSSHCommand() {
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession(SSHConfig.sshusername, SSHConfig.sshHost, 22);
            session.setPassword(SSHConfig.sshpw);
            session.setConfig(config);
            session.connect();
        } catch (JSchException e) {
            LOGGER.log(Level.SEVERE, "Connection with ssh refused");
        }
        LOGGER.log(Level.INFO, "Connected");

        ChannelExec channel = null;
        try {
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(SSHConfig.command);
            channel.setErrStream(System.err);
            channel.connect();
        } catch (JSchException e) {
            LOGGER.log(Level.SEVERE, "Something went wrong when trying to open channel: " + e.getMessage());
        }

    }
}
