package dao.jpa;

import dao.interfaces.DeliveryOrderDao;
import domain.DeliveryOrder;

import javax.ejb.Stateless;

@Stateless
public class DeliveryOrderDaoJpa extends BaseDaoJpa<DeliveryOrder> implements DeliveryOrderDao {
    public DeliveryOrderDaoJpa(){
        super(DeliveryOrder.class);
    }
}
