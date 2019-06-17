package service;

import com.google.gson.Gson;
import dao.interfaces.LocalOrderDao;
import domain.DeliveryOrder;
import domain.LocalOrder;
import dto.OrderDTO;
import messaging.ProducerRabbitMQ;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class LocalOrderService {
    @Inject
    private LocalOrderDao ld;

    @Inject
    private ProducerRabbitMQ prm;

    // todo add serializer
    private final Gson gson = new Gson();

    public LocalOrder create(LocalOrder a) {
        LocalOrder b = ld.create(a);
        OrderDTO orderDTO = new OrderDTO(b);
        prm.sendMsg(gson.toJson(orderDTO), "OrderToKitchen");
        return b;
    }

    public LocalOrder find(String id) {
        return ld.find(id);
    }

    public LocalOrder edit(LocalOrder a) {
        LocalOrder b = ld.edit(a);
        return b;
    }

    public void delete(LocalOrder a) {
        LocalOrder b = ld.find(a.getId());
        ld.delete(a);
    }

    public List<LocalOrder> getAll(String userId) {
        return ld.getAll(userId);
    }
}
