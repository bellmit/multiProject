package data;

import data.interfaces.DeliveryDao;
import domain.Delivery;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class DeliveryDaoJPA extends BaseDaoJPA<Delivery> implements data.interfaces.DeliveryDao {

    public DeliveryDaoJPA() {
        super(Delivery.class);
    }

    @Override
    public List<Delivery> getByEmployeeId(String employeeId) {
        return getEm().createQuery("SELECT d FROM Delivery d WHERE d.employeeId = :employeeId", Delivery.class)
                .setParameter("employeeId", employeeId)
                .getResultList();
    }

    @Override
    public List<Delivery> getAllDeliveries(){
        return getEm().createQuery("SELECT d FROM Delivery d", Delivery.class)
                .getResultList();
    }


}
