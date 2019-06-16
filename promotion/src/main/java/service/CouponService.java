package service;

import dao.interfaces.CouponDao;
import domain.Coupon;
import domain.Discount;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.List;

@Stateless
public class CouponService {

    @Inject
    private CouponDao couponDao;

    @Inject
    private CouponUsageService couponUsageService;

    public void create(Coupon coupon) {
        if (couponDao.findByCode(coupon.getCode()) != null) {
            throw new BadRequestException("Code already exists");
        }
        couponDao.create(coupon);
    }

    public Coupon find(String id) {
        Coupon coupon = couponDao.find(id);
        if (coupon == null) {
            throw new NotFoundException("Coupon not found");
        }
        return coupon;
    }

    public Coupon findByCode(String code) {
        return couponDao.findByCode(code);
    }


    public void edit(Coupon coupon) {
        couponDao.edit(coupon);
    }

    public void delete(String id) {
        Coupon coupon = couponDao.find(id);
        if (coupon == null) {
            throw new NotFoundException("Coupon not found");
        }
        couponDao.delete(coupon);
    }

    public List<Coupon> getAllCoupons() {
        return couponDao.getCoupons();
    }

    public boolean mayUseCode(String code, String userId) {
        Coupon coupon = couponDao.findByCode(code);
        if (coupon == null) {
            return false;
        }
        if (coupon.getUserId() == null || (!userId.equals("") && coupon.getUserId().equals(userId))) {
            int uses = couponUsageService.getUsages(code);
            return coupon.getMaxUses() > uses;
        }
        return false;
    }


    public Discount couponValid(String code, String id) {
        if (mayUseCode(code, id)) {
            Coupon coupon = couponDao.findByCode(code);
            return new Discount(coupon.getRate(), coupon.getType());
        } else {
            throw new BadRequestException("Coupon not valid");
        }
    }
}
