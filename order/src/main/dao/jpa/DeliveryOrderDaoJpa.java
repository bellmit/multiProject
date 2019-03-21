package dao.jpa;

import dao.interfaces.DeliveryOrderDao;
import domain.DeliveryOrder;

public class DeliveryOrderDaoJpa extends BaseDaoJpa<DeliveryOrder> implements DeliveryOrderDao {
    public DeliveryOrderDaoJpa(){
        super(DeliveryOrder.class);
    }
}
