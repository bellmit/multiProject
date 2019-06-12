package socket;

import domain.DeliveryOrder;
import domain.OrderStatus;
import service.DeliveryOrderService;
import service.OrderStatusService;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@ServerEndpoint(value = "/websocket/{delivererId}",
        encoders = OrderEncoder.class,
        decoders = OrderDecoder.class)
public class OrderWebsocket {

    private static final Map<String, Set<Session>> sessions = Collections.synchronizedMap(new HashMap<>());

    public OrderWebsocket() {
    }

    @Inject
    DeliveryOrderService dos;

    @Inject
    OrderStatusService oss;

    @OnOpen
    public void onOpen(@PathParam("delivererId") String delivererId, Session session) throws IOException, EncodeException {
        if (delivererId != null) {
            if (!sessions.containsKey(delivererId)) {
                Set<Session> set = new HashSet<>();
                set.add(session);
                sessions.put(delivererId, set);
            } else {
                Set<Session> set = sessions.get(delivererId);
                set.add(session);
                sessions.put(delivererId, set);
            }
        }
        List<DeliveryOrder> openOrders = new ArrayList<>();
        List<DeliveryOrder> deliveryOrders = dos.getAllDeliveryOrders();
        for (DeliveryOrder deliveryOrder : deliveryOrders) {
            if (deliveryOrder.getStatus().getStatus().toLowerCase() == "open") {
                openOrders.add(deliveryOrder);
            }
        }
        OrderEvent orderEvent = new OrderEvent(openOrders);
        session.getBasicRemote().sendObject(orderEvent);

    }

    @OnClose
    public void onClose(@PathParam("delivererId") String delivererId, Session session) {
        if (delivererId != null) {
            if (sessions.containsKey(delivererId)) {
                for (Session ses : sessions.get(delivererId)) {
                    if (ses.getId() == session.getId()) {
                        sessions.get(delivererId).remove(session);
                    }
                }
            }
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {

    }

    public boolean updateOrders(DeliveryOrder deliveryOrder) throws IOException, EncodeException {
        // todo service get all open orders
        List<DeliveryOrder> deliveryOrders = dos.getAllDeliveryOrders();
        List<DeliveryOrder> openOrders = new ArrayList<>();
        for (DeliveryOrder d : deliveryOrders) {
            if (d.getId() == deliveryOrder.getId()) {
                if (oss.find("delivering") != null) {
                    OrderStatus os = oss.find("delivering");
                    d.setStatus(os);
                    dos.edit(d);
                } else {
                    oss.create(new OrderStatus("delivering"));
                    OrderStatus os = oss.find("delivering");
                    d.setStatus(os);
                    dos.edit(d);
                }
            } else {
                if (d.getStatus().getStatus().toLowerCase() == "open") {
                    openOrders.add(d);
                }
            }
        }
        OrderEvent orderEvent = new OrderEvent(openOrders);
        for (Set<Session> sessionSet : sessions.values()) {
            for (Session ses : sessionSet) {
                ses.getBasicRemote().sendObject(orderEvent);
                return true;
            }
        }
        return false;
    }
}

