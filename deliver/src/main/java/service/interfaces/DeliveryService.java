package service.interfaces;

import domain.Delivery;

import java.util.List;

public interface DeliveryService {
    Delivery getById(String id);

    Delivery getByEmployeeId(String employeeId);

    Delivery addDelivery(List<String> orderList);

    Delivery assignEmployee(String deliveryId, String employeeId);

    Delivery removeById(String id);

    Delivery removeByEmployeeId(String employeeId);

    Delivery editDelivery(String deliveryId, List<String> orderList);
}
