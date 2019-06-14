package socket;

import domain.DeliveryOrder;

import java.util.List;

public class OrderEvent {

    List<DeliveryOrder> deliveryOrders;

    public OrderEvent(List<DeliveryOrder> deliveryOrders) {
        this.deliveryOrders = deliveryOrders;
    }

    public List<DeliveryOrder> getDeliveryOrders() {
        return deliveryOrders;
    }

    public void setDeliveryOrders(List<DeliveryOrder> deliveryOrders) {
        this.deliveryOrders = deliveryOrders;
    }
}
