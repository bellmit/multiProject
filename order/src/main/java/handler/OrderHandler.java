package handler;

import com.google.gson.Gson;
import domain.DeliveryOrder;
import domain.LocalOrder;
import dto.OrderDTO;
import qualifiers.OrderHandlerQ;
import service.DeliveryOrderService;
import service.LocalOrderService;
import util.OrderType;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless @OrderHandlerQ
public class OrderHandler implements ICanHandleIt{
    private static final Logger _pollHandlerLogger = Logger.getLogger(OrderHandler.class.getName());
    private Gson gson = new Gson();

    @Inject
    LocalOrderService los;

    @Inject
    DeliveryOrderService dos;

    @Override
    public boolean handleMessage(String message) {
        try {
            OrderDTO orderDTO = gson.fromJson(message, OrderDTO.class);
            if(orderDTO.getType() == OrderType.LOCAL){
                LocalOrder localOrder = los.find(orderDTO.getId());
                localOrder.setStatus(orderDTO.getStatus());
                los.edit(localOrder);
            } else {
                DeliveryOrder deliveryOrder = dos.find(orderDTO.getId());
                deliveryOrder.setStatus(orderDTO.getStatus());
                dos.edit(deliveryOrder);
            }
            // todo notify delivery app

            return true;
        } catch (Exception ex){
            _pollHandlerLogger.log(Level.SEVERE, ex.toString());
            return false;
        }
    }
}
