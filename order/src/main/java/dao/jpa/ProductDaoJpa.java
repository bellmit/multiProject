package dao.jpa;

import dao.interfaces.ProductDao;
import domain.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProductDaoJpa extends BaseDaoJpa<Product> implements ProductDao {
    @PersistenceContext
    private EntityManager em;

    public ProductDaoJpa() {
        super(Product.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
}