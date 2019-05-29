package dao.Interfaces;


import dao.interfaces.BaseDao;
import domain.TimeSlot;

import java.util.List;

public interface TimeSlotDAO extends BaseDao<TimeSlot> {
    List<TimeSlot> getTimeSlots();
}
