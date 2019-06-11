package data.interfaces;

import dao.interfaces.BaseDao;
import domain.Delivery;

import java.util.List;

public interface DeliveryDao extends BaseDao<Delivery> {

    List<Delivery> getAllDeliveries();

    List<Delivery> getByEmployeeId(String employeeId);
}
