package main.java.DAO;

import main.java.Domain.Reservation;

import java.util.ArrayList;
import java.util.UUID;

public interface ReservationDAO {

    void addReservation(Reservation reservation);

    void removeReservation(Reservation reservation);

    Reservation findById(UUID id);

    ArrayList<Reservation> getReservations();

    void editReservations(Reservation reservation);

}
