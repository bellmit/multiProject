package dao.jpa;

import dao.interfaces.LocalOrderDao;
import domain.DeliveryOrder;
import domain.LocalOrder;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class LocalOrderDaoJpa extends BaseDaoJpa<LocalOrder> implements LocalOrderDao {
    public LocalOrderDaoJpa() {
        super(LocalOrder.class);
    }

    public List<LocalOrder> getAll(String userId){
        Query q = getEntityManager().createNativeQuery("select * from localorder d where d.userid = '" + userId + "'", DeliveryOrder.class);
        return q.getResultList();
    }
}
