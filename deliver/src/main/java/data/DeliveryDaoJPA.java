package data;

import data.interfaces.DeliveryDao;
import domain.Delivery;

import java.util.List;

public class DeliveryDaoJPA extends BaseDaoJPA<Delivery> implements data.interfaces.DeliveryDao {

    public DeliveryDaoJPA() {
        super(Delivery.class);
    }

    public List<Delivery> getAllDeliveries(){
        return getEm().createQuery("SELECT d FROM Delivery d", Delivery.class).getResultList();
    }


}
