package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table
//@NamedQuery(name = "table.findById", query = "SELECT t FROM DinningTable t where  t.id = :id")
public class DiningTable implements Serializable {

    @Id
    private String id;

    @Column(unique = true)
    private int tableNumber;

    private int nrofSeats;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "diningTables")
    private List<Reservation> reservations = new ArrayList<>();

    public DiningTable(int tableNumber, int nrofSeats) {
        this.id = UUID.randomUUID().toString();
        this.tableNumber = tableNumber;
        this.nrofSeats = nrofSeats;
    }

    public DiningTable() {
        this.id = UUID.randomUUID().toString();
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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
