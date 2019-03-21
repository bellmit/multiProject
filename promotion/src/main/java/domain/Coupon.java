package domain;

import util.CouponType;
import util.LocalDateTimeConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Coupon implements Serializable {

    @Id
    private String id;
    private String userId;
    private double flatRate;
    private CouponType type;
    private String code;
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime expDate;
    private int maxUses;

    public Coupon() {
    }

    public Coupon(String userId, double flatRate, CouponType type, String code, LocalDateTime expDate, int maxUses) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.flatRate = flatRate;
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

    public double getFlatRate() {
        return flatRate;
    }

    public void setFlatRate(double flatRate) {
        this.flatRate = flatRate;
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

    public LocalDateTime getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDateTime expDate) {
        this.expDate = expDate;
    }

    public int getMaxUses() {
        return maxUses;
    }

    public void setMaxUses(int maxUses) {
        this.maxUses = maxUses;
    }
}
