package event;

public class SimulationReceiver{

    String orderId;

    String coords;

    public SimulationReceiver(String orderId, String coords) {
        this.orderId = orderId;
        this.coords = coords;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCoords() {
        return coords;
    }

    public void setCoords(String coords) {
        this.coords = coords;
    }
}
