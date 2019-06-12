package service;

import dao.interfaces.CouponUsageDao;
import domain.CouponUsage;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

@Stateless
public class CouponUsageService {

    @Inject
    private CouponService couponService;

    @Inject
    private CouponUsageDao cud;

    public void useCoupon(String code, String id) {
        if (!couponService.findByCode(code, id)) {
            throw new BadRequestException("You are not allowed to use this coupon");
        }
        CouponUsage couponUsage = new CouponUsage();
        couponUsage.setCode(code);
        couponUsage.setUser(id);
        cud.create(couponUsage);
    }

    public int getUsages(String code) {
        return cud.getUsages(code).size();
    }
}
