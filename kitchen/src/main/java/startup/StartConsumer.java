package startup;

import handler.ICanHandleIt;
import messaging.ConsumerRabbitMQ;
import messaging.ProducerRabbitMQ;
import qualifiers.OrderHandlerQ;
import service.LoginPingService;

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

    @Inject @OrderHandlerQ
    private ICanHandleIt hi;

    @PostConstruct
    public void init(){
        ConsumerRabbitMQ cr = new ConsumerRabbitMQ();

        pr.sendMsg("Kitchen-app online", "KitchenToOrder");

        for (String queueName : RABBITMQ_QUEUES) {
            cr.addHandlers(hi);
            cr.runConsumer(queueName);
        }
    }
}
