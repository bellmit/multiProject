package dao.interfaces;

import domain.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationDAO extends BaseDao<Reservation> {
    List<Reservation> getReservationsForDate(Date date);

    List<Reservation> getAll();
}
