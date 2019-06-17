package handler;

import com.google.gson.Gson;
import domain.DeliveryOrder;
import dto.OrderDTO;
import qualifiers.DeliveryOrderHandlerQ;
import service.DeliveryOrderService;
import util.OrderType;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless @DeliveryOrderHandlerQ
public class DeliveryOrderHandler implements ICanHandleIt{
    private static final Logger _deliveryOrderHandlerLogger = Logger.getLogger(DeliveryOrderHandler.class.getName());
    private Gson gson = new Gson();

    @Inject
    DeliveryOrderService dos;

    @Override
    public boolean handleMessage(String message) {
        try {
            OrderDTO orderDTO = gson.fromJson(message, OrderDTO.class);
            if(orderDTO.getType() == OrderType.DELIVERY) {
                DeliveryOrder deliveryOrder = dos.find(orderDTO.getId());
                deliveryOrder.setStatus(orderDTO.getStatus());
                dos.edit(deliveryOrder);

                _deliveryOrderHandlerLogger.log(Level.INFO, "DeliveryOrderHandler manhandled the message");
                return true;
            } else {
                _deliveryOrderHandlerLogger.log(Level.INFO, "DeliveryOrderHandler couldn't handle the message");
                return false;
            }
        } catch (Exception ex){
            _deliveryOrderHandlerLogger.log(Level.SEVERE, ex.toString());
            return false;
        }
    }
}
