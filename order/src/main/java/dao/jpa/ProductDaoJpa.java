package dao.jpa;

import dao.interfaces.ProductDao;
import domain.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ProductDaoJpa extends BaseDaoJpa<Product> implements ProductDao {

    @PersistenceContext(unitName = "orderPU")
    private EntityManager em;

    public ProductDaoJpa() {
        super(Product.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Product> getAll(){
        Query q = getEntityManager().createQuery("SELECT p FROM Product p");
        return q.getResultList();
    }

    @Override
    public List<Product> getAllLocalProducts() {
        Query q = getEntityManager().createQuery("SELECT p FROM Product p WHERE p.type = 0");
        return q.getResultList();
    }

    @Override
    public List<Product> getAllDeliveryProducts() {
        Query q = getEntityManager().createQuery("SELECT p FROM Product p WHERE p.type = 1");
        return q.getResultList();
    }
}