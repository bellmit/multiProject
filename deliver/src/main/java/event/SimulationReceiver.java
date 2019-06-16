package event;

import java.util.List;

public class SimulationReceiver{

    List<String> orderId;

    List<String> coords;

    public SimulationReceiver(List<String> orderId, List<String> coords) {
        this.orderId = orderId;
        this.coords = coords;
    }

    public List<String> getOrderId() {
        return orderId;
    }

    public void setOrderId(List<String> orderId) {
        this.orderId = orderId;
    }

    public List<String> getCoords() {
        return coords;
    }

    public void setCoords(List<String> coords) {
        this.coords = coords;
    }
}
