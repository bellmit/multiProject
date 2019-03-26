package data.interfaces;

import domain.Delivery;

import java.util.List;

public interface DeliveryDao extends BaseDao<Delivery> {

    List<Delivery> getAllDeliveries();
}
