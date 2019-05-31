package service;

import dao.Interfaces.ReservationDAO;
import domain.DinnerType;
import domain.Reservation;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;


@Stateless
public class ReservationService {

    @Inject
    private ReservationDAO reservationDAO;

    public void addReservation(Reservation reservation) {
        if (reservation == null) {
            throw new javax.ws.rs.NotFoundException();
        }
        if (reservation.getTimeSlots().size() == 1) {
            reservation.setType(DinnerType.SINGLECOURSE);
        } else if (reservation.getTimeSlots().size() > 1) {
            reservation.setType(DinnerType.MULTICOURSE);
        }
        reservationDAO.create(reservation);
    }

    public void removeReservation(Reservation reservation) {
        if (reservation == null) {
            throw new javax.ws.rs.NotFoundException();
        }
        reservationDAO.delete(reservation);
    }

    public List<Reservation> getReservations() {
        List<Reservation> reservations = reservationDAO.getAll();
        return reservations;
    }

    public Reservation findById(String id) {
        Reservation r = reservationDAO.find(id);
        if (r == null) {
            throw new javax.ws.rs.NotFoundException();
        }
        return r;
    }

    public void editReservation(Reservation reservation) {
        if (reservation == null) {
            throw new javax.ws.rs.NotFoundException();
        }
        reservationDAO.edit(reservation);
    }

}
