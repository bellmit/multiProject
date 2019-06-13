package socket;

import com.google.gson.Gson;
import event.SimulationEvent;

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
        throw new UnsupportedOperationException();
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException();
    }
}
