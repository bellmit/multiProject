package socket;

import domain.DeliveryOrder;
import service.DeliveryOrderService;
import service.OrderStatusService;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint(value = "/websocket/{delivererId}",
        encoders = OrderEncoder.class,
        decoders = OrderDecoder.class)
public class OrderWebsocket {

    private static final Logger LOGGER = Logger.getLogger(OrderWebsocket.class.getName());

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
        OrderEvent orderEvent = new OrderEvent(dos.getAllDeliveryOrdersByStatus("Waiting for deliverer"));
        session.getBasicRemote().sendObject(orderEvent);

    }

    @OnClose
    public void onClose(@PathParam("delivererId") String delivererId, Session session) {
        if (delivererId != null && sessions.containsKey(delivererId)) {
                for (Session ses : sessions.get(delivererId)) {
                    if (ses.getId().equals(session.getId())) {
                        sessions.get(delivererId).remove(session);
                    }
                }
            }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        LOGGER.log(Level.INFO, "New OnMessage: {0}", message);
    }

    public boolean updateOrders(List<DeliveryOrder> deliveryOrders) {
        return sendOrderEvent(deliveryOrders);
    }

    private boolean sendOrderEvent(List<DeliveryOrder> openOrders){
        OrderEvent orderEvent = new OrderEvent(openOrders);
        for (Set<Session> sessionSet : sessions.values()) {
            int succescount = 0;
            for (Session ses : sessionSet) {
                try {
                    ses.getBasicRemote().sendObject(orderEvent);
                } catch (IOException | EncodeException e) {
                    LOGGER.log(Level.SEVERE,e.getMessage());
                }
                succescount++;
                if(succescount==sessionSet.size()){
                    return true;
                }
            }
        }
        return false;
    }
}

