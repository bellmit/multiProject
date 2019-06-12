package dao.interfaces;

import domain.CouponUsage;

import java.util.List;

public interface CouponUsageDao extends BaseDao<CouponUsage> {
    List<CouponUsage> getUsages(String code);
}
