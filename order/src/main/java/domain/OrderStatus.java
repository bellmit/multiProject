package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderStatus {
    @Id
    private String status;

    public OrderStatus() { }

    public OrderStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
