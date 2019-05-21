package DAO.Interfaces;

import Domain.Reservation;
import dao.interfaces.BaseDao;

import java.util.ArrayList;
import java.util.UUID;

public interface ReservationDAO  extends BaseDao<Reservation> {
    ArrayList<Reservation> getReservations();

}
