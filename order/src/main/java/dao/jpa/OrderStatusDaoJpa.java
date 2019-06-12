package dao.jpa;

import dao.interfaces.OrderStatusDao;
import domain.OrderStatus;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OrderStatusDaoJpa extends BaseDaoJpa<OrderStatus> implements OrderStatusDao {

    @PersistenceContext(unitName = "orderPU")
    private EntityManager em;

    public OrderStatusDaoJpa() {
        super(OrderStatus.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
