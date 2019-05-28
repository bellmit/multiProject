package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@NamedQueries({
        @NamedQuery(name = "reservation.findById", query = "SELECT r FROM Reservation r where  r.uuid = :id")
})
public class Reservation implements Serializable {

    @Id
    private String uuid;

    private int userID;

    private int nrofPeople;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Enumerated(EnumType.STRING)
    private DinnerType type;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "reservation_id")
    private List<TimeSlot> timeSlots;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "reservation_id")
    private List<DiningTable> diningTables;

    public Reservation(int userID, int nrofPeople, Date date, DinnerType type, List<TimeSlot> timeSlots, List<DiningTable> diningTables) {
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

    public List<DiningTable> getDiningTables() {
        return diningTables;
    }

    public void setDiningTables(List<DiningTable> diningTables) {
        this.diningTables = diningTables;
    }

    public void Notify(Reservation r){
        Date startTime = new Date();
        if(r.getDate().getYear()==new Date().getYear()&&r.getDate().getMonth()==new Date().getMonth()&&
                r.getDate().getDay()== new Date().getDay()){
            for (TimeSlot ts: r.getTimeSlots()) {
                long difference = ts.getStartTime().getHours() - new Date().getHours();
                long diffHours = difference / (60 * 60 * 1000) % 24;
                if(difference>=3){
                    //Send notification
                }
            }
        }
    }
}
