package util;

import com.google.gson.Gson;
import dto.OrderDTO;
import messaging.ProducerRabbitMQ;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;

@Stateless
public class Scheduler {

    @Resource
    private TimerService timerService;

    @Inject
    private ProducerRabbitMQ prm;

    private final long preparationTime = 5000; // in ms

    private final String status = "Waiting for deliverer";

    private Gson gson = new Gson();

    @Timeout
    public void afterTimeOut(Timer timer) {
        OrderDTO orderDTO = (OrderDTO) timer.getInfo();
        orderDTO.getStatus().setStatus(status);
        if(orderDTO.getType() == OrderType.LOCAL) {
            prm.sendMsg(gson.toJson(orderDTO), "KitchenToLocalOrder");
        } else {
            prm.sendMsg(gson.toJson(orderDTO), "KitchenToDeliveryOrder");
        }
    }

    public void setNewScheduler(OrderDTO orderDTO) {
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setInfo(orderDTO);
        timerService.createSingleActionTimer(preparationTime, timerConfig);
    }
}
