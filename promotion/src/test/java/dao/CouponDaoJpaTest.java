package dao;

import dao.jpa.CouponDaoJpa;
import domain.Coupon;
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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CouponDaoJpaTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("couponTestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private CouponDaoJpa cdj;

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(CouponDaoJpaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        cdj = new CouponDaoJpa();
        cdj.setEm(em);
    }

    @Test
    public void create(){

       /* Coupon coupon = new Coupon();
        tx.begin();
        cdj.create(coupon);
        tx.commit();
        assertThat(coupon.getId(),is(cdj.find(coupon.getId())));*/
    }

    @Test
    public void delete(){

    }

    @Test
    public void update(){

    }

    @Test
    public void find(){

    }

}
