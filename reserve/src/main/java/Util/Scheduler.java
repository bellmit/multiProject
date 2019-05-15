package Util;

import DAO.Interfaces.ReservationDAO;
import DAO.JPA.ReservationDAOJPA;
import Domain.Reservation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerService;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.TimerConfig;


@Startup
@Singleton
public class Scheduler {

    Reservation reser = null;

    @Resource
    TimerService timerService;

    @PostConstruct
    public void initialize() {
        ReservationDAOJPA reservationDAOJPA = new ReservationDAOJPA();
        ArrayList<Reservation> reservations = reservationDAOJPA.getReservations();
        for (Reservation r:reservations) {
            this.setNewScheduler(r);
        }
    }

    public void setNewScheduler(Reservation r){
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setInfo("MailTimer");
        Date now = new Date();
        Date reservationtime = r.getTimeSlots().get(0).getStartTime();
        long difference = reservationtime.getTime() - now.getTime();
        long x = difference - (10800*1000);
        reser = r;
        timerService.createSingleActionTimer(x,timerConfig);
    }

    @Timeout
    public void afterTimeOut(){
        Mailer m = new Mailer();
        m.send(String.valueOf(reser.getUserID()),"Reservation Notification",
                "Dear sir or madam we'd like to inform you that your reservation will start in 3 hours at " + reser.getTimeSlots().get(0).getStartTime().toString());
    }

    @PreDestroy
    public void clearTimers() {

    }

}