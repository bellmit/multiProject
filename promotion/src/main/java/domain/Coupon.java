package domain;

import util.CouponType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@Entity
@NamedQueries({
        @NamedQuery(name = "coupon.GetAll", query = "SELECT c FROM Coupon c"),
        @NamedQuery(name = "coupon.findByCode", query = "SELECT c FROM Coupon c WHERE c.code = :code")
})
public class Coupon implements Serializable {

    @Id
    private String id;
    private String userId;
    private double rate;
    @Enumerated(EnumType.STRING)
    private CouponType type;
    private String code;
    private Date expDate;
    private int maxUses;

    public Coupon() {
        this.id = UUID.randomUUID().toString();
    }

    public Coupon(String userId, double rate, CouponType type, String code, Date expDate, int maxUses) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.rate = rate;
        this.type = type;
        this.code = code;
        this.expDate = expDate;
        this.maxUses = maxUses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public int getMaxUses() {
        return maxUses;
    }

    public void setMaxUses(int maxUses) {
        this.maxUses = maxUses;
    }
}
