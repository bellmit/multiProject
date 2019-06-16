package domain;

import util.CouponType;

public class Discount {
    private double rate;
    private CouponType type;

    public Discount(double rate, CouponType type) {
        this.rate = rate;
        this.type = type;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public CouponType getType() {
        return type;
    }

    public void setType(CouponType type) {
        this.type = type;
    }
}
