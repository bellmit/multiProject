package main.java.Service;



import main.java.DAO.JPA;
import main.java.DAO.ReservationDAO;
import main.java.Domain.Reservation;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.UUID;


@Stateless
public class ReservationService {

    @Inject @JPA
    private ReservationDAO reservationDAO;

    public void addReservation(Reservation reservation) {
        reservationDAO.addReservation(reservation);
    }

    public void removeReservation(Reservation reservation) {
        reservationDAO.removeReservation(reservation);
    }

    public ArrayList<Reservation> getReservations() {
        return reservationDAO.getReservations();
    }

    public Reservation findById(UUID id) {
        return reservationDAO.findById(id);
    }

    public void editReservation(Reservation reservation){
        reservationDAO.editReservations(reservation);
    }
    public ReservationService() {
    }
}
