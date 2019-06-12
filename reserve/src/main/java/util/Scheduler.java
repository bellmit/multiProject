package util;

import domain.Reservation;
import qualifiers.ReservationScheduler;
import service.UserService;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Stateless
@ReservationScheduler
public class Scheduler {

    @Inject
    private UserService userService;

    @Inject
    private Mailer mailer;

    @Resource
    private TimerService timerService;

    @Timeout
    public void afterTimeOut(Timer timer) {
        Reservation reservation = (Reservation) timer.getInfo();
        mailer.send(userService.find(reservation.getUserID()).getEmail());
    }

    public void setNewScheduler(Reservation reservation) {
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setInfo(reservation);
        Date now = new Date();
        Date reservationTime = reservation.getTimeSlots().get(0).getStartTime();
        long untilReservation = reservationTime.getTime() - now.getTime();
        long threeHoursBeforeReservation = untilReservation - (10800 * 1000);
        if(threeHoursBeforeReservation > 0) {
            timerService.createSingleActionTimer(threeHoursBeforeReservation, timerConfig);
        }
    }

}
