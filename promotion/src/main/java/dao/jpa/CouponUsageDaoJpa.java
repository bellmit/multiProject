package dao.jpa;

import dao.interfaces.CouponUsageDao;
import domain.CouponUsage;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CouponUsageDaoJpa extends BaseDaoJpa<CouponUsage> implements CouponUsageDao {

    @PersistenceContext(unitName = "promotionPU")
    private EntityManager em;

    public CouponUsageDaoJpa() {
        super(CouponUsage.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    public List<CouponUsage> getUsages(String code) {
        TypedQuery<CouponUsage> query = em.createNamedQuery("couponUsage.getCouponUsage", CouponUsage.class);
        query.setParameter("code", code);
        List<CouponUsage> couponUsages = query.getResultList();
        if (couponUsages != null && !couponUsages.isEmpty()) {
            return couponUsages;
        }
        return new ArrayList<>();
    }

}
