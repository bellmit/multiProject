package event;

import java.util.List;

public class SimulationEvent {

    private String lat ;

    private String lon;

    private List<String> orderid;

    private String endlat;

    private String endLon;

    private String startLat;

    private String employeeId;

    public SimulationEvent(String lat, String lon, List<String> orderid, String employeeId) {
        this.lat = lat;
        this.lon = lon;
        this.orderid = orderid;
        this.employeeId  = employeeId;
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

    public String getEndlat() {
        return endlat;
    }

    public void setEndlat(String endlat) {
        this.endlat = endlat;
    }

    public String getEndLon() {
        return endLon;
    }

    public void setEndLon(String endLon) {
        this.endLon = endLon;
    }

    public String getStartLat() {
        return startLat;
    }

    public void setStartLat(String startLat) {
        this.startLat = startLat;
    }
}
