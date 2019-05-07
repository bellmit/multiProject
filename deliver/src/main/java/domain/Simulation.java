package domain;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Simulation implements Runnable {

    String name;
    Thread t;
    String coordinates;
    String firstcoord;

    public Simulation(String name,String coordinates,String firstcoord) {
        this.name = name;
        this.coordinates = coordinates;
        this.firstcoord = firstcoord;
        t = new Thread(this,name);
        t.start();
    }

    public void run() {
        try {
            this.Send();

        }catch (Exception e) {
            System.out.println(e );
        }
    }

    public void Send(){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("coordinates_receiver", true, false, false, null);

            String message = coordinates;

            channel.basicPublish("", "coordinates_receiver",
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.Receive();
    }
    public  void Receive(){
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            final Connection connection = factory.newConnection();
            final Channel channel = connection.createChannel();
            String connstring = "simulation_queue";
            connstring += firstcoord;
            channel.queueDeclare(connstring, true, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            channel.basicQos(1);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");

                System.out.println(" [x] Received '" + message + "'");
                try {
                    //push coords to map
                } finally {
                    System.out.println(" [x] Done");
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                }
            };
            channel.basicConsume(connstring, false, deliverCallback, consumerTag -> {
            });
        }
        catch (Exception e){
            System.out.println(e );
    }
    }
}
