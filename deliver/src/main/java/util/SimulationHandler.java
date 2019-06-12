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
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SimulationHandler {

    boolean keeprunning = true;

    public void startSimulation(String coords,String oderId) throws IOException, TimeoutException {

        String host = "192.168.24.110";
        String user = "root";
        String password = "root";
        String command = " python3 /home/python/simulation.py";
        try{
            Properties config = new Properties();
            config.put("StrictHostKeyChecking","no");
            JSch jsch = new JSch();
            Session session = jsch.getSession(user,host,8022);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            System.out.println("Connected");

            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            channel.setErrStream(System.err);
            channel.connect();
            TimeUnit.SECONDS.sleep(5);
            sendCoords(coords);
            ReceiveCoords(coords,oderId);
        }catch(Exception e){
            String ex = e.toString();
        }
       /* Process p = Runtime.getRuntime().exec(command);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendCoords(coords);
        ReceiveCoords(coords,oderId);
        */
    }

    public void sendCoords(String coords) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.24.110");
         Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();
            channel.queueDeclare("coordinates_receiver4", true, false, false, null);

            String message = coords;

            channel.basicPublish("", "coordinates_receiver4",
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
            connection.close();
    }

    public void ReceiveCoords(String coords,String orderId) throws IOException, TimeoutException {
        String[] coordsSplit = coords.split(",");
        SimulationSocket socket = new SimulationSocket();
        String finalcoord = coordsSplit[3].replace(")","");
        SimulationEvent simulationEvent = new SimulationEvent("","",orderId);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare("simulation_queue"+finalcoord, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        channel.basicQos(1);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
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
    }

}

