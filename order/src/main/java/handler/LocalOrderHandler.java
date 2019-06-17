package handler;

import com.google.gson.Gson;
import domain.LocalOrder;
import dto.OrderDTO;
import qualifiers.LocalOrderHandlerQ;
import service.LocalOrderService;
import util.OrderType;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless @LocalOrderHandlerQ
public class LocalOrderHandler implements ICanHandleIt{
    private static final Logger _localOrderHandlerLogger = Logger.getLogger(LocalOrderHandler.class.getName());
    private Gson gson = new Gson();

    @Inject
    LocalOrderService los;

    @Override
    public boolean handleMessage(String message) {
        try {
            OrderDTO orderDTO = gson.fromJson(message, OrderDTO.class);
            if(orderDTO.getType() == OrderType.LOCAL){
                LocalOrder localOrder = los.find(orderDTO.getId());
                localOrder.setStatus(orderDTO.getStatus());
                los.edit(localOrder);
                _localOrderHandlerLogger.log(Level.INFO, "PollHandler manhandled the message");
                return true;
            } else {
                _localOrderHandlerLogger.log(Level.INFO, "PollHandler couldn't handle the message");
                return false;
            }
        } catch (Exception ex){
            _localOrderHandlerLogger.log(Level.SEVERE, ex.toString());
            return false;
        }
    }
}
