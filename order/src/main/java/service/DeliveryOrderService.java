package service;

import com.google.gson.Gson;
import dao.interfaces.DeliveryOrderDao;
import dao.interfaces.OrderStatusDao;
import domain.DeliveryOrder;
import dto.OrderDTO;
import messaging.ProducerRabbitMQ;
import domain.OrderStatus;
import socket.OrderWebsocket;
import util.OrderType;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class DeliveryOrderService {
    @Inject
    private DeliveryOrderDao dd;

    @Inject
    private OrderStatusDao osd;

    @Inject
    private ProducerRabbitMQ prm;

    private final Gson gson = new Gson();

    private final String statusDeliver = "Waiting for deliverer";

    private OrderWebsocket orderWebsocket = new OrderWebsocket();

    public DeliveryOrder create(DeliveryOrder a){
        DeliveryOrder b = dd.create(a);
        OrderDTO orderDTO = new OrderDTO(b);
        orderDTO.setType(OrderType.DELIVERY);
        prm.sendMsg(gson.toJson(orderDTO), "OrderToKitchen");
        return b;
    }

    public DeliveryOrder find(String id){
        return dd.find(id);
    }

    public List<DeliveryOrder> startDelivery(String id) throws InterruptedException {
        DeliveryOrder deliveryOrder =  find(id);
        OrderStatus status = osd.find("Is being delivered");
        deliveryOrder.setStatus(status);
        edit(deliveryOrder);
        return getAllDeliveryOrdersByStatus(statusDeliver);
    }

    public DeliveryOrder edit(DeliveryOrder a){
        DeliveryOrder b = dd.edit(a);
        if(b.getStatus().getStatus().equals(statusDeliver)){
            orderWebsocket.updateOrders(getAllDeliveryOrdersByStatus(statusDeliver));
        }
        return b;
    }

    public void delete(DeliveryOrder a){
        DeliveryOrder b = dd.find(a.getId());
        dd.delete(b);
    }

    public List<DeliveryOrder> getAll(String userId){
        return dd.getAll(userId);
    }

    public List<DeliveryOrder> getAllDeliveryOrders() { return  dd.getAllDeliveryOrders();}

    public List<DeliveryOrder> getAllDeliveryOrdersByStatus(String status) { return dd.getAllOrdersByStatus(status) ;}
}
