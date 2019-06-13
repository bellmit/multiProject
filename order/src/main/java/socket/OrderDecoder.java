package socket;

import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class OrderDecoder implements Decoder.Text<OrderEvent> {

    Gson gson = new Gson();

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

    }

    @Override
    public void destroy() {

    }
}
