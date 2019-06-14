package service;

import dao.interfaces.ReservationDAO;
import domain.Reservation;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;

@Stateless
public class PingService {

    @Inject
    ReservationDAO reservationDAO;

    public boolean ping() {
        Reservation reservation = new Reservation();
        reservation.setDate(new Date());
        reservation.setUserID("1");
        reservation.setNrofPeople(1);
        reservation.setTimeSlots(new ArrayList<>());
        reservationDAO.delete(reservationDAO.create(reservation));
        return true;
    }
}
