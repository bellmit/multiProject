package dao;

import dao.jpa.TableDAOJPA;
import dao.jpa.TimeSlotDAOJPA;
import domain.DiningTable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiningTableDaoJpaTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("nldTestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private TableDAOJPA tableDAOJPA;

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(TimeSlotDAOJPA.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        tableDAOJPA = new TableDAOJPA();
        tableDAOJPA.setEm(em);
    }

    @Test
    public void editandCreateTest(){
        tx.begin();
        DiningTable diningTable = new DiningTable(1,6);
        tableDAOJPA.create(diningTable);
        tx.commit();
        tx.begin();
        DiningTable dbDiningTable = tableDAOJPA.find(diningTable.getId());
        Assert.assertEquals(diningTable, dbDiningTable);
        diningTable.setNrofSeats(12);
        tableDAOJPA.edit(diningTable);
        tx.commit();
        dbDiningTable = tableDAOJPA.find(diningTable.getId());
        Assert.assertEquals(12, dbDiningTable.getNrofSeats());

    }

    @Test
    public void removeTest(){
        tx.begin();
        DiningTable diningTable2 = new DiningTable(2,4);
        tableDAOJPA.create(diningTable2);
        tx.commit();
        for (DiningTable t: tableDAOJPA.getTables()) {
            tx.begin();
            tableDAOJPA.delete(t);
            tx.commit();
        }
        Assert.assertEquals(0, tableDAOJPA.getTables().size());
    }
}
