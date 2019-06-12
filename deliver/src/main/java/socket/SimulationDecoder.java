package socket;

import event.SimulationEvent;
import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class SimulationDecoder implements Decoder.Text<SimulationEvent> {

    Gson gson = new Gson();

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

    }

    @Override
    public void destroy() {

    }
}
