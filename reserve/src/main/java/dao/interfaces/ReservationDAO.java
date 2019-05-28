package dao.interfaces;

import domain.Reservation;

import java.util.List;

public interface ReservationDAO extends BaseDao<Reservation> {
    List<Reservation> getReservations();

}
