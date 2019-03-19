package dao;

import dao.interfaces.RoleDao;
import dao.jpa.RoleDaoJpa;
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

public class RoleDaoJpaTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("testPU");
    private EntityManager em;
    private EntityTransaction tx;
    private RoleDaoJpa roleDao;

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDaoJpaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        roleDao = new RoleDaoJpa();
        roleDao.setEm(em);
    }

    @Test
    public void exampleTest() {
    }
}
