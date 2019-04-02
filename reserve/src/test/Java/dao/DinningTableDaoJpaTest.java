package dao;

import DAO.JPA.TableDAOJPA;
import DAO.JPA.TimeSlotDAOJPA;
import Domain.DinningTable;
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

public class DinningTableDaoJpaTest {

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
        DinningTable dinningTable = new DinningTable(1,6);
        tableDAOJPA.create(dinningTable);
        tx.commit();
        tx.begin();
        DinningTable dbDinningTable = tableDAOJPA.find(dinningTable.getId());
        Assert.assertEquals(dinningTable, dbDinningTable);
        dinningTable.setNrofSeats(12);
        tableDAOJPA.edit(dinningTable);
        tx.commit();
        dbDinningTable = tableDAOJPA.find(dinningTable.getId());
        Assert.assertEquals(12, dbDinningTable.getNrofSeats());

    }

    @Test
    public void removeTest(){
        tx.begin();
        DinningTable dinningTable2 = new DinningTable(2,4);
        tableDAOJPA.create(dinningTable2);
        tx.commit();
        for (DinningTable t: tableDAOJPA.getTables()) {
            tx.begin();
            tableDAOJPA.delete(t);
            tx.commit();
        }
        Assert.assertEquals(tableDAOJPA.getTables().size(),0);
    }
}
