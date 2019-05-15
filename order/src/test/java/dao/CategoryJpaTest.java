package dao;

import config.props;
import dao.jpa.CategoryDaoJpa;
import domain.Category;
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

public class CategoryJpaTest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(props.testPU);
    private EntityManager em;
    private EntityTransaction tx;
    private CategoryDaoJpa ad;

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryJpaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        ad = new CategoryDaoJpa();
        ad.setEm(em);

    }
/**
    @Test
    public void createAndFind() {
        tx.begin();
        Category a = new Category();
        ad.create(a);
        tx.commit();
        Assert.assertNotNull(ad.find(a.getName()));
    }

    @Test
    public void delete() {
        tx.begin();
        Category a = new Category();
        ad.create(a);
        tx.commit();

        tx.begin();
        ad.delete(a);
        tx.commit();

        Assert.assertNull(ad.find(a.getName()));
    }
    **/
}
