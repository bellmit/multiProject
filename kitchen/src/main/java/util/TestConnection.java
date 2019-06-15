package util;

import messaging.ConsumerRabbitMQ;
import messaging.ProducerRabbitMQ;
import service.LoginPingService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class TestConnection {
    @Inject
    ProducerRabbitMQ pr;

    ConsumerRabbitMQ cr = new ConsumerRabbitMQ();


    @PostConstruct
    public void init(){
        pr.sendMsg("Test", "test");

        cr.runConsumer("test");
    }
}
