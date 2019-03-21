package service;

import dao.interfaces.DeliveryOrderDao;
import domain.DeliveryOrder;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.UUID;

@Stateless
public class DeliveryOrderService {
    @Inject
    private DeliveryOrderDao dd;

    public void create(DeliveryOrder a){
        dd.create(a);
    }

    public DeliveryOrder find(String id){
        return dd.find(id);
    }

    public void edit(DeliveryOrder a){
        dd.edit(a);
    }

    public void delete(DeliveryOrder a){
        dd.delete(a);
    }
}
