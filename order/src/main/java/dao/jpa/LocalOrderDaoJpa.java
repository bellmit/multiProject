package dao.jpa;

import dao.interfaces.LocalOrderDao;
import domain.LocalOrder;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class LocalOrderDaoJpa extends BaseDaoJpa<LocalOrder> implements LocalOrderDao {

    @PersistenceContext(unitName = "orderPU")
    private EntityManager em;

    public LocalOrderDaoJpa() {
        super(LocalOrder.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<LocalOrder> getAll(String userId){
        Query q = getEntityManager().createQuery("select d from LocalOrder d where d.userId = '" + userId + "'", LocalOrder.class);
        return q.getResultList();
    }
}
