package dto;

import domain.DeliveryOrder;
import domain.LocalOrder;
import domain.OrderStatus;
import util.OrderType;

public class OrderDTO {
    private String id;
    private OrderType type;
    private OrderStatus status;

    public OrderDTO() {
    }

    public OrderDTO(LocalOrder localOrder) {
        this.id = localOrder.getId();
        this.type = OrderType.LOCAL;
        this.status = localOrder.getStatus();
    }

    public OrderDTO(DeliveryOrder deliveryOrder) {
        this.id = deliveryOrder.getId();
        this.type = OrderType.DELIVERY;
        this.status = deliveryOrder.getStatus();
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
