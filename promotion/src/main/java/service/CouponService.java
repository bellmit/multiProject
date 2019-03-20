package service;

import dao.interfaces.CouponDao;
import domain.Coupon;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.UUID;

@Stateless
public class CouponService {
    @Inject
    private CouponDao cd;

    public void create(Coupon coupon){
        cd.create(coupon);
    }

    public Coupon find(UUID id){
        return cd.find(id);
    }

    public void edit(Coupon coupon){
        cd.edit(coupon);
    }

    public void delete(Coupon coupon){
        cd.delete(coupon);
    }
}
