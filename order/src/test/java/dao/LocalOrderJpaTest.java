package dao;

import config.props;
import dao.jpa.LocalOrderDaoJpa;
import domain.LocalOrder;
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

public class LocalOrderJpaTest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(props.testPU);
    private EntityManager em;
    private EntityTransaction tx;
    private LocalOrderDaoJpa ad;

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(LocalOrderJpaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        ad = new LocalOrderDaoJpa();
        ad.setEm(em);

    }

    /**
    @Test
    public void createAndFind() {
        tx.begin();
        LocalOrder a = new LocalOrder();
        ad.create(a);
        tx.commit();
        Assert.assertNotNull(ad.find(a.getId()));
    }
    **/

    /**8@Test
    public void delete() {
        tx.begin();
        LocalOrder a = new LocalOrder();
        ad.create(a);
        tx.commit();

        tx.begin();
        ad.delete(a);
        tx.commit();

        Assert.assertNull(ad.find(a.getId()));
    }
     **/
}