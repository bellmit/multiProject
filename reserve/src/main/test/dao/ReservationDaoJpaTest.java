package dao;

import DAO.ReservationDAO;
import DAO.ReservationDAOJPA;
import DAO.TableDAOJPA;
import DAO.TimeSlotDAOJPA;
import Domain.DinnerType;
import Domain.Reservation;
import Domain.Table;
import Domain.TimeSlot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationDaoJpaTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("nldTestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private ReservationDAOJPA reservationDAOJPA;

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(TimeSlotDAOJPA.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        reservationDAOJPA = new ReservationDAOJPA();
        reservationDAOJPA.setEm(em);
    }

    @Test
    public void editandCreateTest(){
        Table table = new Table(1,6);
        TimeSlot ts = new TimeSlot("test",new Date(),new Date());
        List<Table> tables = new ArrayList<>();
        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(ts);
        tables.add(table);
        Reservation reservation = new Reservation(1,3,new Date(), DinnerType.Singlecourse,timeSlots,tables);
        reservationDAOJPA.addReservation(reservation);
        Reservation dbReservation = reservationDAOJPA.findById(reservation.getId());
        Assert.assertEquals(reservation,dbReservation);
        reservation.setNrofPeople(12);
        reservationDAOJPA.editReservations(reservation);
        dbReservation = reservationDAOJPA.findById(reservation.getId());
        Assert.assertEquals(12,reservation.getNrofPeople());

    }

    @Test
    public void removeTest(){
        Table table2 = new Table(1,6);
        TimeSlot ts2 = new TimeSlot("test",new Date(),new Date());
        List<Table> tables2 = new ArrayList<>();
        List<TimeSlot> timeSlots2 = new ArrayList<>();
        timeSlots2.add(ts2);
        tables2.add(table2);
        Reservation reservation2 = new Reservation(1,3,new Date(), DinnerType.Singlecourse,timeSlots2,tables2);
        reservationDAOJPA.addReservation(reservation2);
        for (Reservation r: reservationDAOJPA.getReservations()) {
            reservationDAOJPA.removeReservation(r);
        }
        Assert.assertEquals(reservationDAOJPA.getReservations().size(),0);
    }
}
