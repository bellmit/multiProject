package util;

import dao.jpa.ReservationDAOJPA;
import domain.Reservation;
import service.ReservationService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;


@Singleton
public class Scheduler {

    Reservation reser = new Reservation();

    @Inject
    ReservationService rs;

    @Resource
    TimerService timerService;

    @PostConstruct
    public void initialize() {

            List<Reservation> reservations = rs.getReservations();
            if(!reservations.isEmpty()) {
                for (Reservation r : reservations) {
                    this.setNewScheduler(r);
                }
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
