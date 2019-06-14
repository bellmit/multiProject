package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.util.UUID;

@Entity
@NamedQuery(name = "couponUsage.getCouponUsage", query = "SELECT c FROM CouponUsage c WHERE c.code = :code")
public class CouponUsage {

    @Id
    private String id;

    private String code;

    private String user;

    public CouponUsage() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String uuid) {
        this.id = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
