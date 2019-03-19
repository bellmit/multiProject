package dao.jpa;

import dao.interfaces.ProductDao;
import domain.Product;

import javax.ejb.Stateless;

@Stateless
public class ProductDaoJpa extends BaseDaoJpa<Product> implements ProductDao {
    public ProductDaoJpa() {
        super(Product.class);
    }
}