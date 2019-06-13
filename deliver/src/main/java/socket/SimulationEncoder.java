package socket;

import com.google.gson.Gson;
import event.SimulationEvent;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;


public class SimulationEncoder implements Encoder.Text<SimulationEvent> {
    Gson gson = new Gson();

    @Override
    public String encode(SimulationEvent object) throws EncodeException {
        return gson.toJson(object,SimulationEvent.class);
    }

    @Override
    public void init(EndpointConfig config) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException();
    }
}
