package socket;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class OrderEncoder implements Encoder.Text<OrderEvent> {

    Gson gson = new Gson();

    @Override
    public String encode(OrderEvent object) throws EncodeException {
        return gson.toJson(object,OrderEvent.class);
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
