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
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint(value = "/websocket/{delivererId}",
        encoders = OrderEncoder.class,
        decoders = OrderDecoder.class)
public class OrderWebsocket {

    private static final Logger LOGGER = Logger.getLogger(OrderWebsocket.class.getName());

    private static final Map<String, Set<Session>> sessions = Collections.synchronizedMap(new HashMap<>());

    public OrderWebsocket() {
        //We need an empty constructor
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
            if (deliveryOrder.getStatus().getStatus().equalsIgnoreCase("open")) {
                openOrders.add(deliveryOrder);
            }
        }
        OrderEvent orderEvent = new OrderEvent(openOrders);
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
        //we dont need to do anything here
    }

    public boolean updateOrders(DeliveryOrder deliveryOrder) {
        // service get all open orders implement
        String id = "delivering";
        List<DeliveryOrder> deliveryOrders = dos.getAllDeliveryOrders();
        List<DeliveryOrder> openOrders = new ArrayList<>();
        for (DeliveryOrder d : deliveryOrders) {
            if (d.getId().equals(deliveryOrder.getId())) {
                if (oss.find(id) != null) {
                    OrderStatus os = oss.find(id);
                    d.setStatus(os);
                    dos.edit(d);
                } else {
                    oss.create(new OrderStatus(id));
                    OrderStatus os = oss.find(id);
                    d.setStatus(os);
                    dos.edit(d);
                }
            } else {
                if (d.getStatus().getStatus().equalsIgnoreCase("open")) {
                    openOrders.add(d);
                }
            }
        }
        return sendOrderEvent(openOrders);
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

