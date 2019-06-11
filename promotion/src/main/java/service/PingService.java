package service;

import dao.interfaces.CouponDao;
import domain.Coupon;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PingService {

    @Inject
    CouponDao couponDao;

    public boolean ping() {
        couponDao.delete(couponDao.find(couponDao.create(new Coupon()).getId()));
        return true;
    }
}
