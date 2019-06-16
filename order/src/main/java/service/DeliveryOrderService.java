package service;

import dao.interfaces.DeliveryOrderDao;
import dao.interfaces.OrderStatusDao;
import domain.DeliveryOrder;
import domain.OrderStatus;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class DeliveryOrderService {
    @Inject
    private DeliveryOrderDao dd;

    @Inject
    private OrderStatusDao osd;

    public DeliveryOrder create(DeliveryOrder a){
        return dd.create(a);
    }

    public DeliveryOrder find(String id){
        return dd.find(id);
    }

    public List<DeliveryOrder> startDelivery(String id){
        DeliveryOrder deliveryOrder =  find(id);
        OrderStatus status = osd.find("Is being delivered");
        deliveryOrder.setStatus(status);
        edit(deliveryOrder);
        return getAllDeliveryOrdersByStatus("Waiting for deliverer");
    }

    public DeliveryOrder edit(DeliveryOrder a){
        return dd.edit(a);
    }

    public void delete(DeliveryOrder a){
        DeliveryOrder b = dd.find(a.getId());
        dd.delete(a);
    }

    public List<DeliveryOrder> getAll(String userId){
        return dd.getAll(userId);
    }

    public List<DeliveryOrder> getAllDeliveryOrders() { return  dd.getAllDeliveryOrders();}

    public List<DeliveryOrder> getAllDeliveryOrdersByStatus(String status) { return dd.getAllOrdersByStatus(status) ;}
}
