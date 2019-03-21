package dao.jpa;

import dao.interfaces.OrderStatusDao;
import domain.OrderStatus;

import javax.ejb.Stateless;

@Stateless
public class OrderStatusDaoJpa extends BaseDaoJpa<OrderStatus> implements OrderStatusDao {
    public OrderStatusDaoJpa() {
        super(OrderStatus.class);
    }
}
