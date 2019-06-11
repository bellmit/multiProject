package service;

import dao.interfaces.ReservationDAO;
import domain.Reservation;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PingService {

    @Inject
    ReservationDAO reservationDAO;

    public boolean ping() {
        reservationDAO.delete(reservationDAO.create(new Reservation()));
        return true;
    }
}
