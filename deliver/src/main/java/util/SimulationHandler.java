package util;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import event.SimulationEvent;
import socket.SimulationSocket;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimulationHandler {

    private boolean keeprunning = true;

    private static final Logger LOGGER = Logger.getLogger(SimulationHandler.class.getName());

    private static final String HOST = "192.168.24.110";


    public void startSimulation(String coords,String oderId)  {



        String user = "root";
        String ssh = "root";
        String command = " python3 /home/python/simulation.py";
        try{
            Properties config = new Properties();
            config.put("StrictHostKeyChecking","no");
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, HOST,22);
            session.setPassword(ssh);
            session.setConfig(config);
            session.connect();
            LOGGER.log(Level.INFO,"Connected");

            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            channel.setErrStream(System.err);
            channel.connect();
            TimeUnit.SECONDS.sleep(5);
            sendCoords(coords);
            receiveCoords(coords,oderId);
        }catch(Exception e){
           LOGGER.log(Level.SEVERE,e.getMessage()+" ssh");
        }

    }

    public void sendCoords(String coords)  {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            channel.queueDeclare("coordinates_receiver4", true, false, false, null);

            channel.basicPublish("", "coordinates_receiver4",
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    coords.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + coords + "'");
        } catch (TimeoutException | IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage()+ " rabbit");
        }
    }

    private void receiveCoords(String coords, String orderId) {
        String[] coordsSplit = coords.split(",");
        SimulationSocket socket = new SimulationSocket();
        String finalcoord = coordsSplit[3].replace(")","");
        SimulationEvent simulationEvent = new SimulationEvent("","",orderId);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        try {
            final Connection connection = factory.newConnection();
            final Channel channel = connection.createChannel();
            channel.queueDeclare("simulation_queue" + finalcoord, true, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            channel.basicQos(1);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            if(message.contains("stops")){
                keeprunning = false;
                simulationEvent.setLat("stop");
                simulationEvent.setLon("stop");
                socket.sendUpdateSimulation(simulationEvent);
                System.out.println("Stopped");
            }
            String[] splitMessage = message.split(",");
            String msg1 =  splitMessage[2].replace("[","");
            String msg2 = splitMessage[3].replace("]","");
            simulationEvent.setLat(msg1);
            simulationEvent.setLon(msg2);
            socket.sendUpdateSimulation(simulationEvent);
            System.out.println(" [x] Received ' Lat: " + msg1 + "Lon: "+msg2+"'" +message);
                System.out.println(" [x] Done");
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);



        };
        while (keeprunning) {
            channel.basicConsume("simulation_queue" + finalcoord, false, deliverCallback, consumerTag -> {
            });
        }
        connection.close();
        } catch (TimeoutException | IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage()+ " rabbit");
        }
    }

}

