package service;

import data.interfaces.DeliveryDao;
import domain.Delivery;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PingService {

    //@Inject
    //private LogService logService;

    @Inject
    private DeliveryDao deliveryDao;

    public boolean ping() {
        deliveryDao.delete(deliveryDao.find(deliveryDao.create(new Delivery()).getDeliveryId()));
        return true;
    }
}
