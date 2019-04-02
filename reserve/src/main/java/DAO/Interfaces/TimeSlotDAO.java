package DAO.Interfaces;


import Domain.TimeSlot;

import java.util.ArrayList;
import java.util.UUID;

public interface TimeSlotDAO extends  BaseDAO<TimeSlot>{


    ArrayList<TimeSlot> getTimeSlots();

}
