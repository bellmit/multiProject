package service;

import dao.interfaces.ProductDao;
import domain.Product;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PingService {

    @Inject
    ProductDao productDao;

    public boolean ping() {
        productDao.delete(productDao.find(productDao.create(new Product()).getId()));
        return true;
    }
}
