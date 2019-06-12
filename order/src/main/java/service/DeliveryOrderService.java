package service;

import dao.interfaces.DeliveryOrderDao;
import domain.DeliveryOrder;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class DeliveryOrderService {
    @Inject
    private DeliveryOrderDao dd;

    public DeliveryOrder create(DeliveryOrder a){
        return dd.create(a);
    }

    public DeliveryOrder find(String id){
        return dd.find(id);
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
}
