package dao;

import dao.jpa.ReservationDAOJPA;
import dao.jpa.TimeSlotDAOJPA;
import domain.DiningTable;
import domain.DinnerType;
import domain.Reservation;
import domain.TimeSlot;
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
    public void editandCreateTest() {
        tx.begin();
        DiningTable diningTable = new DiningTable(1, 6);
        TimeSlot ts = new TimeSlot("test", new Date(), new Date());
        List<DiningTable> diningTables = new ArrayList<>();
        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(ts);
        diningTables.add(diningTable);
        Reservation reservation = new Reservation(1, 3, new Date(), DinnerType.SINGLECOURSE, timeSlots, diningTables);
        reservationDAOJPA.create(reservation);
        tx.commit();
        Reservation dbReservation = null;
        dbReservation = reservationDAOJPA.find(reservation.getId());
        Assert.assertEquals(reservation, dbReservation);
        reservation.setNrofPeople(12);
        tx.begin();
        reservationDAOJPA.edit(reservation);
        tx.commit();
        dbReservation = reservationDAOJPA.find(reservation.getId());
        Assert.assertEquals(12, dbReservation.getNrofPeople());

    }

    @Test
    public void removeTest() {
        tx.begin();
        DiningTable diningTable2 = new DiningTable(1, 6);
        TimeSlot ts2 = new TimeSlot("test", new Date(), new Date());
        List<DiningTable> tables2 = new ArrayList<>();
        List<TimeSlot> timeSlots2 = new ArrayList<>();
        timeSlots2.add(ts2);
        tables2.add(diningTable2);
        Reservation reservation2 = new Reservation(1, 3, new Date(), DinnerType.SINGLECOURSE, timeSlots2, tables2);
        reservationDAOJPA.create(reservation2);
        tx.commit();
        for (Reservation r : reservationDAOJPA.getAll()) {
            tx.begin();
            reservationDAOJPA.delete(r);
            tx.commit();
        }
        Assert.assertEquals(0, reservationDAOJPA.getAll().size());
    }

}
