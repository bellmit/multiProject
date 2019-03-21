package domain;

import util.OrderType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Product implements Serializable {
    private UUID id;
    private String name;
    @OneToMany
    private OrderType type;
    private double price;
    private double vat;

    public Product() {
        this.id = UUID.randomUUID();
    }

    public Product(String name, OrderType type, double price, double vat) {
        this();
        this.name = name;
        this.type = type;
        this.price = price;
        this.vat = vat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }
}
