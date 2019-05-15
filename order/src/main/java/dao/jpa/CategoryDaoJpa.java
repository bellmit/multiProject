package dao.jpa;

import dao.interfaces.CategoryDao;
import domain.Category;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class CategoryDaoJpa extends BaseDaoJpa<Category> implements CategoryDao {
    public CategoryDaoJpa(){
        super(Category.class);
    }

    @Override
    public List<Category> getAll() {
        Query q = getEntityManager().createNativeQuery("select * from category", Category.class);
        return q.getResultList();
    }
}
