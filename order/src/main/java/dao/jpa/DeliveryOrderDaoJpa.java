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

    @PersistenceContext(unitName = "orderPU")
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
        Query q = getEntityManager().createQuery("select d from DeliveryOrder d where d.userId = '" + userId + "'", DeliveryOrder.class);
        return q.getResultList();
    }

    public List<DeliveryOrder> getAllDeliveryOrders(){
        Query q = getEntityManager().createQuery("select d from DeliveryOrder d",DeliveryOrder.class);
        return q.getResultList();
    }

    public List<DeliveryOrder> getAllOrdersByStatus(String status){
        Query q = getEntityManager().createQuery("select d from DeliveryOrder d where d.status = '" + status + "'",DeliveryOrder.class);
        return q.getResultList();
    }
}
