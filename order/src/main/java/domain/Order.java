package domain;

import util.LocalDateTimeConverter;
import util.OrderType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@MappedSuperclass
public abstract class Order implements Serializable {
    @Id
    private String id;
    @NotNull
    private String userId;
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime date;
    @Positive
    private double totalPrice;
    @Positive
    private double totalVat;
    private String paymentId;
    @ManyToOne
    private OrderStatus status;
    private String couponId;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> products;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalVat() {
        return totalVat;
    }

    public void setTotalVat(double totalVat) {
        this.totalVat = totalVat;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
