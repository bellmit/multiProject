package handler;

import com.google.gson.Gson;
import dto.OrderDTO;
import qualifiers.OrderHandlerQ;
import service.KitchenService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@OrderHandlerQ
public class OrderHandler implements ICanHandleIt {
    private static final Logger _orderHandlerLogger = Logger.getLogger(OrderHandler.class.getName());
    private Gson gson = new Gson();

    @Inject
    KitchenService ks;

    @Override
    public boolean handleMessage(String message) {
        try {
            OrderDTO orderDTO = gson.fromJson(message, OrderDTO.class);

            // todo send to scheduler

            return true;
        } catch (Exception ex) {
            _orderHandlerLogger.log(Level.SEVERE, ex.toString());
            return false;
        }
    }
}
