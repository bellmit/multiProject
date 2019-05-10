package Service;

import DAO.Interfaces.TimeSlotDAO;
import DAO.JPA.TimeSlotDAOJPA;
import Domain.TimeSlot;


import javax.ejb.Stateless;
import java.util.ArrayList;
import javax.inject.Inject;


@Stateless
public class TimeSlotService {

    @Inject
    private TimeSlotDAO tsDAO;

    public void addTimeSlot(TimeSlot ts) {
        if(ts == null){
            throw new javax.ws.rs.NotFoundException();
        }
        tsDAO.create(ts);
    }

    public void removeTimeSlot(TimeSlot ts) {
        if(ts == null){
            throw new javax.ws.rs.NotFoundException();
        }
        tsDAO.delete(ts);
    }

    public ArrayList<TimeSlot> getTimeSlots() {
        ArrayList<TimeSlot> timeSlots = tsDAO.getTimeSlots();
        if(timeSlots == null){
            throw new javax.ws.rs.NotFoundException();
        }
        return timeSlots;
    }

    public TimeSlot findById(String id) {
        TimeSlot ts = tsDAO.find(id);
        if(ts == null){
            throw new javax.ws.rs.NotFoundException();
        }
        return ts;
    }

    public void edit(TimeSlot ts){
        if(ts == null){
            throw new javax.ws.rs.NotFoundException();
        }
        tsDAO.edit(ts);
    }
}
