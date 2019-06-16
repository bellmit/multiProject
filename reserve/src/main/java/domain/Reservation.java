package domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@NamedQuery(name = "reservation.findByDate", query = "SELECT r FROM Reservation r WHERE r.date = :date")
public class Reservation implements Serializable {

    @Id
    private String uuid;

    @NotNull
    private String userID;

    @Positive
    private int nrofPeople;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;

    @Enumerated(EnumType.STRING)
    private DinnerType type;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "reservation_id")
    private List<TimeSlot> timeSlots = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "reservation_tables")
    private List<DiningTable> diningTables = new ArrayList<>();

    public Reservation(String userID, int nrofPeople, Date date, DinnerType type, List<TimeSlot> timeSlots, List<DiningTable> diningTables) {
        this.uuid = UUID.randomUUID().toString();
        this.userID = userID;
        this.nrofPeople = nrofPeople;
        this.date = date;
        this.type = type;
        this.timeSlots = timeSlots;
        this.diningTables = diningTables;
    }

    public Reservation() {
        this.uuid = UUID.randomUUID().toString();
    }

    public String getId() {
        return uuid;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
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

    public List<DiningTable> getDiningTables() {
        return diningTables;
    }

    public void setDiningTables(List<DiningTable> diningTables) {
        this.diningTables = diningTables;
    }

}
