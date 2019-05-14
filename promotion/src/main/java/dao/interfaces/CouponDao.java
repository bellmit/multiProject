package dao.interfaces;

import domain.Coupon;

import java.util.ArrayList;

public interface CouponDao extends BaseDao<Coupon> {
    ArrayList<Coupon> getCoupons();
}
