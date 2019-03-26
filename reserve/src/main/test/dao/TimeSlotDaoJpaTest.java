package dao;

import DAO.TimeSlotDAOJPA;
import Domain.TimeSlot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseCleaner;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.*;

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
    public void editandCreateTest(){
        TimeSlot ts = new TimeSlot("tets",new Date(),new Date());
        timeSlotDAOJPA.addTimeSlot(ts);
        TimeSlot dbtimeslot = timeSlotDAOJPA.findById(ts.getId());
        Assert.assertEquals(dbtimeslot,ts);
        ts.setName("newname");
        timeSlotDAOJPA.editTimeSlots(ts);
        dbtimeslot = timeSlotDAOJPA.findById(ts.getId());
        Assert.assertEquals("newname",dbtimeslot.getName());

    }

    @Test
    public void removeTest(){
        TimeSlot ts = new TimeSlot("test2",new Date(),new Date());
        timeSlotDAOJPA.addTimeSlot(ts);
        for (TimeSlot t: timeSlotDAOJPA.getTimeSlots()) {
            timeSlotDAOJPA.removeTimeSlot(t);
        }
        Assert.assertEquals(timeSlotDAOJPA.getTimeSlots().size(),0);
    }
}
