package service;

import data.interfaces.DeliveryDao;
import domain.Delivery;

import javax.inject.Inject;

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
}
