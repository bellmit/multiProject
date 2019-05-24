package dao.Interfaces;

import domain.Reservation;
import dao.interfaces.BaseDao;

import java.util.List;

public interface ReservationDAO extends BaseDao<Reservation> {
    List<Reservation> getReservations();

}
