package domain;

import javax.persistence.Entity;

@Entity
public class LocalOrder extends Order{

    private int tableNumber;

    public LocalOrder(){
        super();
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
