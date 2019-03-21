package service;

import data.interfaces.DeliveryDao;
import domain.Delivery;

import javax.inject.Inject;
import java.util.List;

public class DeliveryService implements service.interfaces.DeliveryService {

    private DeliveryDao deliveryDao;

    @Inject
    public void setDeliveryDao(DeliveryDao deliveryDao) {
        this.deliveryDao = deliveryDao;
    }

    @Override
    public Delivery getById(String id) {
        return deliveryDao.find(id);
    }

    @Override
    public Delivery assignEmployee(String deliveryId, String employeeId) {
        return null;
    }

    @Override
    public Delivery removeById(String id) {
        return null;
    }

    @Override
    public Delivery removeByEmployeeId(String employeeId) {
        return null;
    }

    @Override
    public Delivery editDelivery(String deliveryId, List<String> orderList) {
        return null;
    }

    @Override
    public Delivery getByEmployeeId(String employeeId) {
        return null;
    }

    @Override
    public Delivery addDelivery(List<String> orderList) {
        return null;
    }
}
