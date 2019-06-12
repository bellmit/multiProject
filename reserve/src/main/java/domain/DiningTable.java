package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table
//@NamedQuery(name = "table.findById", query = "SELECT t FROM DinningTable t where  t.id = :id")
public class DiningTable implements Serializable {

    @Id
    private String id;

    private int tableNumber;

    private int nrofSeats;

    public DiningTable(int tableNumber, int nrofSeats) {
        this.id = UUID.randomUUID().toString();
        this.tableNumber = tableNumber;
        this.nrofSeats = nrofSeats;
    }

    public DiningTable() {
    }

    public String getId() {
        return id;
    }


    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getNrofSeats() {
        return nrofSeats;
    }

    public void setNrofSeats(int nrofSeats) {
        this.nrofSeats = nrofSeats;
    }
}