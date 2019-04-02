package Service;

import DAO.Interfaces.ReservationDAO;
import Domain.Reservation;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;


@Stateless
public class ReservationService {

    @Inject
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

    public Reservation findById(String id) {
        return reservationDAO.findById(id);
    }

    public void editReservation(Reservation reservation){
        reservationDAO.editReservations(reservation);
    }
    public ReservationService() {
    }
}
