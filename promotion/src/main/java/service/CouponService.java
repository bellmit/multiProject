package service;

import dao.interfaces.CouponDao;
import domain.Coupon;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CouponService {
    @Inject
    private CouponDao cd;

    public void create(Coupon coupon) {
        if (cd.findByCode(coupon.getCode()) != null) {
            throw new BadRequestException("Code already exists");
        }
        cd.create(coupon);
    }

    public Coupon find(String id) {
        Coupon coupon = cd.find(id);
        if (coupon == null) {
            throw new NotFoundException("Coupon not found");
        }
        return coupon;
    }

    public void edit(Coupon coupon) {
        cd.edit(coupon);
    }

    public void delete(String id) {
        Coupon coupon = cd.find(id);
        if (coupon == null) {
            throw new NotFoundException("Coupon not found");
        }
        cd.delete(coupon);
    }

    public List<Coupon> getAllCoupons() {
        return cd.getCoupons();
    }
}
