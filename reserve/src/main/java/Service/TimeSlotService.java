package Service;

import DAO.Interfaces.TimeSlotDAO;
import Domain.TimeSlot;


import javax.ejb.Stateless;
import java.util.ArrayList;
import javax.inject.Inject;


@Stateless
public class TimeSlotService {

    @Inject 
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

    public TimeSlot findById(String id) {
        return tsDAO.findById(id);
    }

    public void edit(TimeSlot ts){
        tsDAO.editTimeSlots(ts);
    }
}
