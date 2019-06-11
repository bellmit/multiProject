package data;

import dao.jpa.BaseDaoJpa;
import data.interfaces.DeliveryDao;
import domain.Delivery;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class DeliveryDaoJPA extends BaseDaoJpa<Delivery> implements DeliveryDao {

    @PersistenceContext(unitName = "deliverPU")
    private EntityManager em;

    public DeliveryDaoJPA() {
        super(Delivery.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Delivery> getByEmployeeId(String employeeId) {
        return getEntityManager().createQuery("SELECT d FROM Delivery d WHERE d.employeeId = :employeeId", Delivery.class)
                .setParameter("employeeId", employeeId)
                .getResultList();
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        return getEntityManager().createQuery("SELECT d FROM Delivery d", Delivery.class)
                .getResultList();
    }


}
