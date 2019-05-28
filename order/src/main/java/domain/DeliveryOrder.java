package domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class DeliveryOrder extends Order{
    @ManyToOne
    private Address address;

    public DeliveryOrder(String userId, LocalDateTime date, double totalPrice, double totalVat, OrderStatus orderStatus, List<Product> products, Address address) {
        super();
        this.setId(UUID.randomUUID().toString());
        this.setUserId(userId);
        this.setDate(date);
        this.setTotalPrice(totalPrice);
        this.setTotalVat(totalVat);
        this.setStatus(orderStatus);
        this.setProducts(products);
        this.address = address;
    }

    public DeliveryOrder(){
        super();
        setId(UUID.randomUUID().toString());
        setDate(LocalDateTime.now());
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
