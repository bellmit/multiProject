package DAO.Interfaces;

import Domain.Reservation;

import java.util.ArrayList;
import java.util.UUID;

public interface ReservationDAO  extends  BaseDAO<Reservation>{

    ArrayList<Reservation> getReservations();

}
