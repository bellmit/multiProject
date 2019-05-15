package dao.jpa;

import dao.interfaces.CouponDao;
import domain.Coupon;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

@Stateless
public class CouponDaoJpa extends BaseDaoJpa<Coupon> implements CouponDao {

    @PersistenceContext(unitName = "nldPU")
    private EntityManager em;

    public CouponDaoJpa() {
        super(Coupon.class);
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public ArrayList<Coupon> getCoupons() {
        TypedQuery<Coupon> query = em.createNamedQuery("coupon.GetAll", Coupon.class);
        return  new ArrayList<>(query.getResultList());
    }
}
