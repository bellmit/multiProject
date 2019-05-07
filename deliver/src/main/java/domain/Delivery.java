package domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Delivery {
    @Id
    private String deliveryId = UUID.randomUUID().toString();
    private String employeeId;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> orderIdList;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Route> routes;

    public Delivery(String employeeId, Set<String> orderIdList) {
        this.employeeId = employeeId;
        this.orderIdList = orderIdList;
    }
    public Delivery(Set<String> orderIdList) {
        this.orderIdList = orderIdList;
        this.routes = new HashSet<>();
    }
    public Delivery(){
        this.routes = new HashSet<>();
        this.orderIdList = new HashSet<>();
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Set<String> getOrderIdList() {
        return orderIdList;
    }

    public void setOrderIdList(Set<String> orderIdList) {
        this.orderIdList = orderIdList;
    }

    public Set<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }

    public void addRoute(Route route) {
        this.routes.add(route);
    }

    public void removeRoute(Route route){
        this.routes.remove(route);
    }

    public void startDeliverySim(Delivery delivery){
        int i = 0;
        for (Route r:delivery.getRoutes()) {
            i++;
            String coordinates = r.toString();
            String firstCoord = Double.toString(r.getStartLocation().getLongitude());
            String name = "Route"+ Integer.toString(i);
            Simulation s = new Simulation(name,coordinates,firstCoord);
        }
    }
}
