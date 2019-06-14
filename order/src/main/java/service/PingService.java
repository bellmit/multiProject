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
        Product product = new Product();
        product.setName("test");
        product.setPrice(1);
        product.setVat(1);
        productDao.delete(productDao.find(productDao.create(product).getId()));
        return true;
    }
}
