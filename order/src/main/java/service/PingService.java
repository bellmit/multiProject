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
        Product product = new Product();
        product.setName("test");
        product.setPrice(1);
        product.setVat(1);
        productDao.delete(productDao.find(productDao.create(product).getId()));
        return true;
    }
}
