package dao.jpa;

import dao.interfaces.CouponDao;
import domain.Coupon;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CouponDaoJpa extends BaseDaoJpa<Coupon> implements CouponDao {

    @PersistenceContext
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

    @Override
    public Coupon findByCode(String code) {
        TypedQuery<Coupon> query = em.createNamedQuery("coupon.findByCode", Coupon.class);
        query.setParameter("code", code);
        List<Coupon> coupons = query.getResultList();
        if (coupons != null && !coupons.isEmpty()) {
            return coupons.get(0);
        }
        return null;
    }
}
