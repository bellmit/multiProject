package dao.jpa;

import dao.interfaces.CouponDao;
import domain.Coupon;

import javax.persistence.EntityManager;

public class CouponDaoJpa extends BaseDaoJpa<Coupon> implements CouponDao {
    public CouponDaoJpa() {
        super(Coupon.class);
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
