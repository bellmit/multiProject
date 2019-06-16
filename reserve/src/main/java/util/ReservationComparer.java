package util;

import domain.Reservation;

import java.util.Comparator;

public class ReservationComparer implements Comparator<Reservation> {

    @Override
    public int compare(Reservation t, Reservation t1) {
        return t.getTimeSlots().get(0).getStartTime().compareTo(t1.getTimeSlots().get(0).getStartTime());
    }
}
