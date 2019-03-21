package domain;

import javax.persistence.Entity;

@Entity
public class DeliveryOrder extends Order{
    private Address address;

    public DeliveryOrder(){
        super();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public DeliveryOrder(Address address) {
        super();
        this.address = address;
    }
}
