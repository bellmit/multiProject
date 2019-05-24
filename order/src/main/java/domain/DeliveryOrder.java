package domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class DeliveryOrder extends Order{
    @OneToOne
    private Address address;

    public DeliveryOrder(Address address) {
        super();
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
