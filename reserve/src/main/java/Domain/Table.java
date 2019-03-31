package Domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@javax.persistence.Table(name = "dinningtable")
//@NamedQueries({
//        @NamedQuery(name = "table.findById", query = "SELECT t FROM dinningtable t where  t.id = :id")
//})
public class Table implements Serializable {

    @Id
    private String id;

    private int tableNumber;

    private int nrofSeats;

    public Table(int tableNumber, int nrofSeats) {
        this.id = UUID.randomUUID().toString();
        this.tableNumber = tableNumber;
        this.nrofSeats = nrofSeats;
    }

    public Table() {
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
