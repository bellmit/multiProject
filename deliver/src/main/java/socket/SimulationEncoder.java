package socket;

import com.google.gson.Gson;
import event.SimulationEvent;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SimulationEncoder implements Encoder.Text<SimulationEvent> {
    Gson gson = new Gson();
    private static final Logger LOGGER = Logger.getLogger(SimulationEncoder.class.getName());

    @Override
    public String encode(SimulationEvent object) throws EncodeException {
        return gson.toJson(object,SimulationEvent.class);
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
