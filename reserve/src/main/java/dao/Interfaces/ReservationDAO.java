package dao.Interfaces;

import dao.interfaces.BaseDao;
import domain.Reservation;

import java.util.List;

public interface ReservationDAO extends BaseDao<Reservation> {
    List<Reservation> getReservations();

}
