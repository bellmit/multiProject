package util;

import dao.jpa.ReservationDAOJPA;
import domain.Reservation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;


@Startup
@Singleton
public class Scheduler {

    Reservation reser = new Reservation();

    @Resource
    TimerService timerService;

    @Inject
    ReservationDAOJPA reservationDAOJPA;

    @PostConstruct
    public void initialize() {
        try {
            List<Reservation> reservations = reservationDAOJPA.getReservations();
            for (Reservation r : reservations) {
                this.setNewScheduler(r);
            }
        } catch (Exception e) {
        }
    }

    public void setNewScheduler(Reservation r) {
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setInfo("MailTimer");
        Date now = new Date();
        Date reservationtime = r.getTimeSlots().get(0).getStartTime();
        long difference = reservationtime.getTime() - now.getTime();
        long x = difference - (10800 * 1000);
        reser = r;
        timerService.createSingleActionTimer(x, timerConfig);
    }

    @Timeout
    public void afterTimeOut() {
        Mailer m = new Mailer();
        m.send(String.valueOf(reser.getUserID()), "Reservation Notification",
                "Dear sir or madam we'd like to inform you that your reservation will start in 3 hours at " + reser.getTimeSlots().get(0).getStartTime().toString());
    }

    @PreDestroy
    public void clearTimers() {

    }

}
