package Domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(name = "timeslot.findById", query = "SELECT ts FROM TimeSlot ts where  ts.id = :id")
})
public class TimeSlot implements Serializable {

    @Id
    private UUID id;

    private String name;

    private Date startTime;

    private Date endTime;

    public TimeSlot(String name, Date startTime, Date endTime) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TimeSlot() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
