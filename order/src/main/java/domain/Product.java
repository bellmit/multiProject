package domain;

import util.OrderType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
public class Product implements Serializable {
    @Id
    private String id;
    @NotEmpty
    private String name;
    @ElementCollection(fetch = FetchType.EAGER)
    private Collection<OrderType> type;
    @Positive
    private double price;
    @Positive
    private double vat;
    @ManyToOne
    private Category category;

    public Product() {
        this.id = UUID.randomUUID().toString();
        type = new ArrayList<>();
    }

    public Product(String name, Category category, Collection<OrderType> type, double price, double vat) {
        this();
        this.name = name;
        this.category = category;
        this.type = type;
        this.price = price;
        this.vat = vat;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<OrderType> getType() {
        return type;
    }

    public void setType(Collection<OrderType> type) {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
