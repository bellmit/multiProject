package dao;

import org.junit.Before;
import org.junit.Test;

public class CouponDaoJpaTest {

   /* EntityManagerFactory emf = Persistence.createEntityManagerFactory("couponTestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private CouponDaoJpa cdj;*/

    @Before
    public void setUp() {
     /*   try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(CouponDaoJpaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        cdj = new CouponDaoJpa();
        cdj.setEm(em);*/
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
