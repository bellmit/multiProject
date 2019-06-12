package dao.interfaces;

import domain.DeliveryOrder;

import java.util.List;

public interface DeliveryOrderDao extends BaseDao<DeliveryOrder> {
    List<DeliveryOrder> getAll(String userId);
    List<DeliveryOrder> getAllDeliveryOrders();
    List<DeliveryOrder> getAllOrdersByStatus(String status);
}
