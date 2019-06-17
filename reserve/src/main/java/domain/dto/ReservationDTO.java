package domain.dto;

import domain.DinnerType;
import domain.Reservation;
import util.DomainObjectConverter;

import java.util.Date;
import java.util.List;

public class ReservationDTO {
    private String user;
    private int numberOfPeople;
    private Date date;
    private DinnerType type;
    private String time;
    private List<String> diningTables;

    public ReservationDTO(Reservation reservation) {
        this.user = reservation.getUserID();
        this.numberOfPeople = reservation.getNrofPeople();
        this.date = reservation.getDate();
        this.type = reservation.getType();
        this.time = DomainObjectConverter.convertTimeSlots(reservation.getTimeSlots());
        this.diningTables = DomainObjectConverter.convertDiningTables(reservation.getDiningTables());
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getDiningTables() {
        return diningTables;
    }

    public void setDiningTables(List<String> diningTables) {
        this.diningTables = diningTables;
    }
}
