package socket;

import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDecoder implements Decoder.Text<OrderEvent> {

    Gson gson = new Gson();

    private static final Logger LOGGER = Logger.getLogger(OrderDecoder.class.getName());

    @Override
    public OrderEvent decode(String s) throws DecodeException {
        return gson.fromJson(s,OrderEvent.class);
    }

    @Override
    public boolean willDecode(String s) {
        return false;
    }

    @Override
    public void init(EndpointConfig config) {
        LOGGER.log(Level.INFO, "Init called");
    }

    @Override
    public void destroy() {
        LOGGER.log(Level.INFO, "destroy called");
    }
}
