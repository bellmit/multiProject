package socket;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderEncoder implements Encoder.Text<OrderEvent> {

    Gson gson = new Gson();

    private static final Logger LOGGER = Logger.getLogger(OrderEncoder.class.getName());

    @Override
    public String encode(OrderEvent object) throws EncodeException {
        return gson.toJson(object,OrderEvent.class);
    }

    @Override
    public void init(EndpointConfig config) {
        LOGGER.log(Level.INFO, "Init called");
    }

    @Override
    public void destroy() {
        LOGGER.log(Level.INFO, "Destroy called");
    }
}
