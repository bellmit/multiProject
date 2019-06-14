package service;

import dao.interfaces.AddressDao;
import domain.Address;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PingService {

    @Inject
    AddressDao addressDao;

    public boolean ping() {
        addressDao.delete(addressDao.find(addressDao.create(new Address()).getId()));
        return true;
    }
}
