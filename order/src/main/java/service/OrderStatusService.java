package service;

import dao.interfaces.OrderStatusDao;
import domain.OrderStatus;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class OrderStatusService {
    @Inject
    private OrderStatusDao od;

    public OrderStatus create(OrderStatus a) {
        return od.create(a);
    }

    public OrderStatus find(String id) {
        return od.find(id);
    }

    public OrderStatus edit(OrderStatus a) {
        return od.edit(a);
    }

    public void delete(OrderStatus a) {
        OrderStatus b = od.find(a.getStatus());
        od.delete(b);
    }
}
