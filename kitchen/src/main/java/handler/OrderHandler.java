package handler;

import com.google.gson.Gson;
import dto.OrderDTO;
import qualifiers.OrderHandlerQ;
import util.Scheduler;

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
    private Scheduler scheduler;

    @Override
    public boolean handleMessage(String message) {
        try {
            OrderDTO orderDTO = gson.fromJson(message, OrderDTO.class);
            scheduler.setNewScheduler(orderDTO);
            _orderHandlerLogger.log(Level.INFO, "Order sent to scheduler");

            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
