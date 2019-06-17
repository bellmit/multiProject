package dto;

import domain.OrderStatus;
import util.OrderType;

public class OrderDTO {
    private String id;
    private OrderType type;
    private OrderStatus status;

    public OrderDTO() {
    }

    public OrderDTO(String id, OrderType type, OrderStatus status) {
        this.id = id;
        this.type = type;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
