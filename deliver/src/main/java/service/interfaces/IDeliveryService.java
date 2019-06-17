package service.interfaces;

import domain.Delivery;
import domain.Route;

import java.util.List;
import java.util.Set;

public interface IDeliveryService {
    Delivery getById(String id);

    List<Delivery> getByEmployeeId(String employeeId);

    Delivery addDelivery(Set<String> orderList);

    Delivery createDelivery(Delivery delivery);

    Delivery assignEmployee(String deliveryId, String employeeId);

    void removeById(String id);

    void removeByEmployeeId(String employeeId);

    Delivery editDelivery(String deliveryId, Set<String> orderList);

    Delivery addRoute(String deliveryId, Route route);

    Set<Route> getRoutes(String deliveryId);

    List<Delivery> getAll();
}
