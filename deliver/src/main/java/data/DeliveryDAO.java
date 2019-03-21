package data;

import data.interfaces.DeliveryDao;
import domain.Delivery;

public class DeliveryDAO extends BaseDaoJPA<Delivery> implements DeliveryDao {

    public DeliveryDAO() {
        super(Delivery.class);
    }


}
