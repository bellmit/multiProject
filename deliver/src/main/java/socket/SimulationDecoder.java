package socket;

import com.google.gson.Gson;
import event.SimulationEvent;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimulationDecoder implements Decoder.Text<SimulationEvent> {

    Gson gson = new Gson();

    private static final Logger LOGGER = Logger.getLogger(SimulationDecoder.class.getName());

    @Override
    public SimulationEvent decode(String s) throws DecodeException {
        return  gson.fromJson(s,SimulationEvent.class);
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
        LOGGER.log(Level.INFO, "Destroy called");
    }
}
