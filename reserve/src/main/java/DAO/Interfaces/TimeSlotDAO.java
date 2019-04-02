package DAO;


import Domain.TimeSlot;

import java.util.ArrayList;
import java.util.UUID;

public interface TimeSlotDAO {

    void addTimeSlot(TimeSlot timeSlot);

    void removeTimeSlot(TimeSlot timeSlot);

    TimeSlot findById(String id);

    ArrayList<TimeSlot> getTimeSlots();

    void editTimeSlots(TimeSlot ts);
}
