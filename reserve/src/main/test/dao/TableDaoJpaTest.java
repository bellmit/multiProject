package dao;

import DAO.TableDAOJPA;
import DAO.TimeSlotDAOJPA;
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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableDaoJpaTest {

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
        Table table = new Table(1,6);
        tableDAOJPA.addTable(table);
        Table dbTable = tableDAOJPA.findById(table.getId());
        Assert.assertEquals(table,dbTable);
        table.setNrofSeats(12);
        tableDAOJPA.editTables(table);
        dbTable = tableDAOJPA.findById(table.getId());
        Assert.assertEquals(12,dbTable.getNrofSeats());

    }

    @Test
    public void removeTest(){
        Table table2 = new Table(2,4);
        tableDAOJPA.addTable(table2);
        for (Table t: tableDAOJPA.getTables()) {
            tableDAOJPA.removeTable(t);
        }
        Assert.assertEquals(tableDAOJPA.getTables().size(),0);
    }
}
