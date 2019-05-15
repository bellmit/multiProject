package service;

import dao.interfaces.OrderStatusDao;
import domain.OrderStatus;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.UUID;

@Stateless
public class OrderStatusService {
    @Inject
    private OrderStatusDao od;

    public void create(OrderStatus a){
        od.create(a);
    }

    public OrderStatus find(String id){
        return od.find(id);
    }

    public void edit(OrderStatus a){
        od.edit(a);
    }

    public void delete(OrderStatus a){
        od.delete(a);
    }
}
