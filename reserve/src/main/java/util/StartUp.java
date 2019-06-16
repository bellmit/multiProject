package util;

import domain.Reservation;
import qualifiers.ReservationScheduler;
import service.ReservationService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.List;

@Startup
@Singleton
public class StartUp {

    @Inject
    @ReservationScheduler
    private Scheduler scheduler;

    @Inject
    private ReservationService reservationService;

    @PostConstruct
    public void initialize() {
        List<Reservation> reservations = reservationService.getAll();
        for (Reservation r : reservations) {
            scheduler.setNewScheduler(r);
        }
    }
}
