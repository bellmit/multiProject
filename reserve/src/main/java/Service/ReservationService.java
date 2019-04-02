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
        if(reservation == null){
            throw new javax.ws.rs.NotFoundException();
        }
        reservationDAO.create(reservation);
    }

    public void removeReservation(Reservation reservation) {
        if(reservation == null){
            throw new javax.ws.rs.NotFoundException();
        }
        reservationDAO.delete(reservation);
    }

    public ArrayList<Reservation> getReservations() {
        ArrayList<Reservation> reservations = reservationDAO.getReservations();
        if(reservations==null){
            throw new javax.ws.rs.NotFoundException();
        }
        return reservations;
    }

    public Reservation findById(String id) {
        Reservation r = reservationDAO.find(id);
        if(r == null){
            throw new javax.ws.rs.NotFoundException();
        }
        return r;
    }

    public void editReservation(Reservation reservation){
        if(reservation == null){
            throw new javax.ws.rs.NotFoundException();
        }
        reservationDAO.edit(reservation);
    }
    public ReservationService() {
    }
}
