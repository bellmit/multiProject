package dao.jpa;

import dao.interfaces.LocalOrderDao;
import domain.LocalOrder;

import javax.ejb.Stateless;

@Stateless
public class LocalOrderDaoJpa extends BaseDaoJpa<LocalOrder> implements LocalOrderDao {
    public LocalOrderDaoJpa() {
        super(LocalOrder.class);
    }
}
