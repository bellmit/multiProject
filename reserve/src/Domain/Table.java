package Domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(name = "table.findById", query = "SELECT t FROM Table t where  t.id = :id")
})
public class Table implements Serializable {

    @Id
    private UUID id;

    private int tableNumber;

    private int nrofSeats;

    public Table(int tableNumber, int nrofSeats) {
        this.id = UUID.randomUUID();
        this.tableNumber = tableNumber;
        this.nrofSeats = nrofSeats;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
