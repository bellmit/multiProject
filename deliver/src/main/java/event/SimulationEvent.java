package event;

import java.util.List;

public class SimulationEvent {

    String lat ;

    String lon;

    List<String> orderid;

    public SimulationEvent(String lat, String lon, List<String> orderid) {
        this.lat = lat;
        this.lon = lon;
        this.orderid = orderid;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public List<String> getOrderid() {
        return orderid;
    }

    public void setOrderid(List<String> orderid) {
        this.orderid = orderid;
    }

}
