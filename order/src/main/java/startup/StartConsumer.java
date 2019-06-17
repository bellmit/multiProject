package startup;

import handler.ICanHandleIt;
import messaging.ConsumerRabbitMQ;
import messaging.ProducerRabbitMQ;
import qualifiers.DeliveryOrderHandlerQ;
import qualifiers.LocalOrderHandlerQ;
import qualifiers.StringHandlerQ;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import static messaging.RabbitMQConfig.RABBITMQ_QUEUES;

@Startup
@Singleton
public class StartConsumer {
    @Inject
    private ProducerRabbitMQ pr;

    @Inject @LocalOrderHandlerQ
    private ICanHandleIt localOrderHandler;

    @Inject @DeliveryOrderHandlerQ
    private ICanHandleIt deliveryOrderHandler;

    @Inject @StringHandlerQ
    private ICanHandleIt stringHandler;

    @PostConstruct
    public void init(){
        pr.sendMsg("Order-app online", "OrderToKitchen");

        ConsumerRabbitMQ cr = new ConsumerRabbitMQ();
        cr.addHandlers(localOrderHandler, stringHandler);
        cr.runConsumer("KitchenToLocalOrder");

        ConsumerRabbitMQ cr2 = new ConsumerRabbitMQ();
        cr2.addHandlers(deliveryOrderHandler, stringHandler);
        cr2.runConsumer("KitchenToDeliveryOrder");

        ConsumerRabbitMQ cr3 = new ConsumerRabbitMQ();
        cr3.addHandlers(deliveryOrderHandler, stringHandler);
        cr3.runConsumer("DeliverToOrder");

    }
}
