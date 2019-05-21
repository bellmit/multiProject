package dao.interfaces;

import domain.Coupon;

import java.util.ArrayList;
import java.util.List;

public interface CouponDao extends BaseDao<Coupon> {
    List<Coupon> getCoupons();
    Coupon findByCode(String name);
}
