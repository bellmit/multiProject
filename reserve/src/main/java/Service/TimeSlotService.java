package main.java.Service;


import main.java.DAO.JPA;
import main.java.DAO.TimeSlotDAO;
import main.java.Domain.TimeSlot;

import javax.ejb.Stateless;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.UUID;
import javax.inject.Inject;


@Stateless
public class TimeSlotService {

    @Inject @JPA
    private TimeSlotDAO tsDAO;

    public void addTimeSlot(TimeSlot ts) {
        tsDAO.addTimeSlot(ts);
    }

    public void removeTimeSlot(TimeSlot ts) {
        tsDAO.removeTimeSlot(ts);
    }

    public ArrayList<TimeSlot> getTables() {
        return tsDAO.getTimeSlots();
    }

    public TimeSlot findById(UUID id) {
        return tsDAO.findById(id);
    }

    public void edit(TimeSlot ts){
        tsDAO.editTimeSlots(ts);
    }
}
