package event;

        public class SimulationEvent {

    String lat ;

    String lon;

    String orderid;

    public SimulationEvent(String lat, String lon, String orderid) {
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

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

}
