package service;

import dao.interfaces.AddressDao;
import domain.Address;
import domain.Component;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Level;

@Stateless
public class PingService {

    @Inject
    private LogService logService;

    @Inject
    AddressDao addressDao;

    public boolean ping() {
        addressDao.delete(addressDao.find(addressDao.create(new Address()).getId()));
        return true;
    }

}
