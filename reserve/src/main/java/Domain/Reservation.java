package main.java.Domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@NamedQueries({
        @NamedQuery(name = "reservation.findById", query = "SELECT r FROM Reservation r where  r.id = :id")
})
public class Reservation implements Serializable {

    @Id
    private UUID id;

    private int userID;

    private int nrofPeople;

    private Date date;

    private DinnerType type;

    @OneToMany
    private List<TimeSlot> timeSlots;

    @OneToMany
    private List<Table> tables;

    public Reservation(int userID, int nrofPeople, Date date, DinnerType type, List<TimeSlot> timeSlots, List<Table> tables) {
        this.id = UUID.randomUUID();
        this.userID = userID;
        this.nrofPeople = nrofPeople;
        this.date = date;
        this.type = type;
        this.timeSlots = timeSlots;
        this.tables = tables;
    }

    public Reservation() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getNrofPeople() {
        return nrofPeople;
    }

    public void setNrofPeople(int nrofPeople) {
        this.nrofPeople = nrofPeople;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DinnerType getType() {
        return type;
    }

    public void setType(DinnerType type) {
        this.type = type;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}
