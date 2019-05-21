package dao.jpa;

import dao.interfaces.CategoryDao;
import domain.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class CategoryDaoJpa extends BaseDaoJpa<Category> implements CategoryDao {

    @PersistenceContext
    private EntityManager em;

    public CategoryDaoJpa() {
        super(Category.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Category> getAll() {
        Query q = getEntityManager().createNativeQuery("select * from category", Category.class);
        return q.getResultList();
    }
}
