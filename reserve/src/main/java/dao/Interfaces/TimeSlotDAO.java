package dao.Interfaces;


import domain.TimeSlot;
import dao.interfaces.BaseDao;

import java.util.List;

public interface TimeSlotDAO extends BaseDao<TimeSlot> {
    List<TimeSlot> getTimeSlots();
}
