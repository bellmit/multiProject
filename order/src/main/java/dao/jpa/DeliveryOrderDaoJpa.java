package dao.jpa;

import dao.interfaces.DeliveryOrderDao;
import domain.DeliveryOrder;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class DeliveryOrderDaoJpa extends BaseDaoJpa<DeliveryOrder> implements DeliveryOrderDao {

    @PersistenceContext
    private EntityManager em;

    public DeliveryOrderDaoJpa(){
        super(DeliveryOrder.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<DeliveryOrder> getAll(String userId){
        Query q = getEntityManager().createNativeQuery("select * from deliveryorder d where d.userid = '" + userId + "'", DeliveryOrder.class);
        return q.getResultList();
    }
}
