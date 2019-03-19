package DAO;

import Domain.Reservation;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.enterprise.inject.Default;


@Stateless @Default
public class ReservationDAOColl implements ReservationDAO {

    CopyOnWriteArrayList<Reservation> reservations = new CopyOnWriteArrayList<>();


    @Override
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    @Override
    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    @Override
    public Reservation findById(UUID id) {
        for (Reservation reservation : reservations)
        {
            if(reservation.getId()==id){
                return reservation;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Reservation> getReservations() {
        return new ArrayList<Reservation>(reservations);
    }
}
