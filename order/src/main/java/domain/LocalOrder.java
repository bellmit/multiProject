package domain;

import javax.persistence.Entity;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class LocalOrder extends Order{
    @Positive
    private int tableNumber;

    public LocalOrder(){
        super();
        this.setId(UUID.randomUUID().toString());
        this.setDate(LocalDateTime.now());
    }

    public LocalOrder(String userId, LocalDateTime date, double totalPrice, double totalVat, OrderStatus orderStatus, List<Product> products, int tableNumber) {
        super();
        this.setId(UUID.randomUUID().toString());
        this.setUserId(userId);
        this.setDate(date);
        this.setTotalPrice(totalPrice);
        this.setTotalVat(totalVat);
        this.setStatus(orderStatus);
        this.setProducts(products);
        this.tableNumber = tableNumber;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
}
