package messaging;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import domain.OrderStatus;
import dto.OrderDTO;
import event.SimulationEvent;
import socket.SimulationSocket;
import util.OrderType;
import util.SimulationHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimulationMessageReceiver {

    private static final Logger LOGGER = Logger.getLogger(SimulationHandler.class.getName());

    public SimulationMessageReceiver() {
        //empty constructor
    }

    public void receiveCoords(List<String> coords, List<String> orderId, String currentid, String delivererId) {
        SimulationSocket socket = new SimulationSocket();
        SimulationEvent simulationEvent = new SimulationEvent("", "", orderId,delivererId);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RabbitMQConfig.RABBITMQ_IP);

        String finalcoord = getFinalCoord(coords);

        try {
            final Connection connection = factory.newConnection();
            final Channel channel = connection.createChannel();

            channel.exchangeDeclare("nldexchange", "direct");

            Map<String, Object> args = new HashMap<String, Object>();
            args.put("x-dead-letter-exchange", "nldexchange");

            channel.queueDeclare("deadletter" + finalcoord, false, false, false, args);
            channel.queueDeclare("simulation_queue" + finalcoord, true, false, false, null);
            LOGGER.log(Level.INFO, " [*] Waiting for messages. To exit press CTRL+C");

            channel.basicQos(1);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                if (message.contains("stops")&&!orderId.isEmpty()) {
                    sendOrderDTO(orderId.get(0),"Done");
                    SimulationMessageFormater.FormatClose(simulationEvent,currentid,socket);
                    SimulationMessageCleanUp.clean(channel,finalcoord,connection);
                    orderId.remove(currentid);
                    coords.remove(0);
                    SimulationHandler simulationHandler = new SimulationHandler();
                    SimulationMessageFormater.FormatMessage(simulationEvent, message, socket);
                    simulationHandler.startSimulation(coords, orderId, orderId.get(0),delivererId);
                } else {
                    SimulationMessageFormater.FormatMessage(simulationEvent, message, socket);
                    if(connection.isOpen()){
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);}
                }
            };

            DeliverCallback deadletterCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                LOGGER.log(Level.INFO, "deadmessage" + message);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            };

            channel.basicConsume("simulation_queue" + finalcoord, false, deliverCallback, consumerTag -> {
            });
            channel.basicConsume("deadletter" + finalcoord, false, deadletterCallback, consumerTag -> {

            });

        } catch (TimeoutException | IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage() + " rabbit");
        }
    }

    public void sendOrderDTO(String orderid,String status){
        Gson gson = new Gson();
        OrderStatus orderStatus = new OrderStatus(status);
        OrderDTO dto = new  OrderDTO(orderid, OrderType.DELIVERY,orderStatus);
        SimulationMessageSender sms = new SimulationMessageSender();
        sms.sendStatusUpdate(gson.toJson(dto),"DeliverToOrder");
    }

    public String getFinalCoord(List<String> coords){
        String[] coordsSplit = coords.get(0).split(",");
        return coordsSplit[3].replace(")", "");
    }
}
