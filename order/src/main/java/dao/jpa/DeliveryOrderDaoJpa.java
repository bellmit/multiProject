package dao.jpa;

import dao.interfaces.DeliveryOrderDao;
import domain.DeliveryOrder;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class DeliveryOrderDaoJpa extends BaseDaoJpa<DeliveryOrder> implements DeliveryOrderDao {
    public DeliveryOrderDaoJpa(){
        super(DeliveryOrder.class);
    }

    public List<DeliveryOrder> getAll(String userId){
        Query q = getEntityManager().createNativeQuery("select * from deliveryorder d where d.userid = '" + userId + "'", DeliveryOrder.class);
        return q.getResultList();
    }
}
