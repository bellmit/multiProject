package dao;

import dao.jpa.UserDaoJpa;
import domain.User;
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

public class UserDaoJpaTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("nldTestPU");
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
    public void createAndFindUser() {
        tx.begin();
        User user = new User();
        userDao.create(user);
        tx.commit();
        Assert.assertNotNull(userDao.find(user.getId()));
    }

    @Test
    public void editUser() {
        tx.begin();
        User user = new User();
        userDao.create(user);
        tx.commit();

        tx.begin();
        String email = "test@test.test";
        user.setEmail(email);
        userDao.edit(user);
        tx.commit();

        Assert.assertEquals(userDao.find(user.getId()).getEmail(), email);
    }

    @Test
    public void deleteUser() {
        tx.begin();
        User user = new User();
        userDao.create(user);
        tx.commit();

        tx.begin();
        userDao.delete(user);
        tx.commit();

        Assert.assertNull(userDao.find(user.getId()));
    }
}
