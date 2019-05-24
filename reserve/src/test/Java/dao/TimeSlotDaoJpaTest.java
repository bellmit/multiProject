package dao;

import dao.jpa.TimeSlotDAOJPA;
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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimeSlotDaoJpaTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("nldTestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private TimeSlotDAOJPA timeSlotDAOJPA;

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(TimeSlotDAOJPA.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        timeSlotDAOJPA = new TimeSlotDAOJPA();
        timeSlotDAOJPA.setEm(em);
    }

    @Test
    public void editandCreateTest() {
        tx.begin();
        TimeSlot ts = new TimeSlot("tets", new Date(), new Date());
        timeSlotDAOJPA.create(ts);
        tx.commit();
        TimeSlot dbtimeslot = timeSlotDAOJPA.find(ts.getId());
        Assert.assertEquals(dbtimeslot, ts);
        tx.begin();
        ts.setName("newname");
        timeSlotDAOJPA.edit(ts);
        tx.commit();
        dbtimeslot = timeSlotDAOJPA.find(ts.getId());
        Assert.assertEquals("newname", dbtimeslot.getName());

    }

    @Test
    public void removeTest() {
        tx.begin();
        TimeSlot ts = new TimeSlot("test2", new Date(), new Date());
        timeSlotDAOJPA.create(ts);
        tx.commit();
        for (TimeSlot t : timeSlotDAOJPA.getTimeSlots()) {
            tx.begin();
            timeSlotDAOJPA.delete(t);
            tx.commit();
        }
        Assert.assertEquals(0, timeSlotDAOJPA.getTimeSlots().size());
    }
}
