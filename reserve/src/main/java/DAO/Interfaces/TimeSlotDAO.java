package DAO.Interfaces;


import Domain.TimeSlot;
import dao.interfaces.BaseDao;

import java.util.ArrayList;
import java.util.UUID;

public interface TimeSlotDAO extends BaseDao<TimeSlot> {
    ArrayList<TimeSlot> getTimeSlots();
}
