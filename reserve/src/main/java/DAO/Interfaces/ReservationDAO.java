package DAO;

import Domain.Reservation;

import java.util.ArrayList;
import java.util.UUID;

public interface ReservationDAO {

    void addReservation(Reservation reservation);

    void removeReservation(Reservation reservation);

    Reservation findById(String id);

    ArrayList<Reservation> getReservations();

    void editReservations(Reservation reservation);

}
