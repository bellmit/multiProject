package service.interfaces;

import domain.Delivery;

public interface DeliveryService {
    Delivery getById(String id);

    Delivery getByEmployeeId(String employeeId);
}
