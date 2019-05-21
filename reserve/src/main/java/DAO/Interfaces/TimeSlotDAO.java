package DAO.Interfaces;


import Domain.TimeSlot;
import dao.interfaces.BaseDao;

import java.util.List;

public interface TimeSlotDAO extends BaseDao<TimeSlot> {
    List<TimeSlot> getTimeSlots();
}
