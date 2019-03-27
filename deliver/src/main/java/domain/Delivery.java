package domain;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

@Entity
public class Delivery {
    @Id
    private String deliveryId = UUID.randomUUID().toString();
    private String employeeId;
    @ElementCollection
    private List<String> orderIdList;
    @ElementCollection
    private List<Route> routes;

    public Delivery(String employeeId, List<String> orderIdList) {
        this.employeeId = employeeId;
        this.orderIdList = orderIdList;
    }
    public Delivery(List<String> orderIdList) {
        this.orderIdList = orderIdList;
    }
    public Delivery(){

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

    public List<String> getOrderIdList() {
        return orderIdList;
    }

    public void setOrderIdList(List<String> orderIdList) {
        this.orderIdList = orderIdList;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public void addRoute(Route route) {
        this.routes.add(route);
    }

    public void removeRoute(Route route){
        this.routes.remove(route);
    }
}
