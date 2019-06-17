package startup;

import handler.ICanHandleIt;
import qualifiers.StringHandlerQ;
import messaging.ConsumerRabbitMQ;
import messaging.ProducerRabbitMQ;
import qualifiers.OrderHandlerQ;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import static messaging.RabbitMQConfig.RABBITMQ_QUEUES;

//@Startup
@Singleton
public class StartConsumer {
    @Inject
    private ProducerRabbitMQ pr;

    @Inject @OrderHandlerQ
    private ICanHandleIt orderHandler;

    @Inject @StringHandlerQ
    private ICanHandleIt stringHandler;

    @PostConstruct
    public void init(){
        ConsumerRabbitMQ cr = new ConsumerRabbitMQ();

        pr.sendMsg("Kitchen-app online", "KitchenToDeliveryOrder");
        pr.sendMsg("Kitchen-app online", "KitchenToLocalOrder");

        for (String queueName : RABBITMQ_QUEUES) {
            cr.addHandlers(orderHandler, stringHandler);
            cr.runConsumer(queueName);
        }
    }
}
