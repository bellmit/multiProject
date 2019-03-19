package DAO;

import Domain.TimeSlot;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.enterprise.inject.Default;


@Stateless @Default
public class TimeSlotDAOColl{ /*} implements TimeSlotDAO{

    CopyOnWriteArrayList<TimeSlot> timeSlots = new CopyOnWriteArrayList<>();


    @Override
    public void addTimeSlot(TimeSlot ts) {
        timeSlots.add(ts);
    }

    @Override
    public void removeTimeSlot(TimeSlot ts) {
        timeSlots.remove(ts);
    }

    @Override
    public TimeSlot findById(UUID id) {
        for (TimeSlot ts : timeSlots)
        {
            if(ts.getId()==id){
                return ts;
            }
        }
        return null;
    }

    @Override
    public ArrayList<TimeSlot> getTimeSlots() {
        return new ArrayList<TimeSlot>(timeSlots);
    }
    */
}
