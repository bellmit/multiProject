package domain;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class LocalOrder extends Order{

    private int tableNumber;

    public LocalOrder(){
        super();
        setId(UUID.randomUUID().toString());
        setDate(LocalDateTime.now());
    }

    public LocalOrder(int tableNumber) {
        super();

        this.tableNumber = tableNumber;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
}
