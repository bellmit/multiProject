package dao;

import dao.jpa.RoleDaoJpa;
import dao.jpa.UserDaoJpa;
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

public class UserDaoJpaTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("testPU");
    private EntityManager em;
    private EntityTransaction tx;
    private UserDaoJpa userDao;

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDaoJpaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        userDao = new UserDaoJpa();
        userDao.setEm(em);
    }

    @Test
    public void exampleTest() {
    }
}
