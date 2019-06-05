package util;

import dao.jpa.ReservationDAOJPA;
import domain.Reservation;
import domain.TimeSlot;
import service.ReservationService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Singleton
@Startup
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
            Reservation r = new Reservation();
            List<TimeSlot> timeSlots = new ArrayList<>();
            Date reserdate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(reserdate);
            calendar.add(Calendar.SECOND,30);
            calendar.add(Calendar.HOUR_OF_DAY,3);
            Date time = calendar.getTime();
            timeSlots.add(new TimeSlot("test",time,new Date()));
            r.setTimeSlots(timeSlots);
            setNewScheduler(r);
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
        m.send();
    }

    @PreDestroy
    public void clearTimers() {

    }

}
