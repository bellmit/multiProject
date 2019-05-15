package domain;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

//todo remove

public class Payment implements Serializable {
    private String id;
    @NotNull
    private String paymentMethod;
    private boolean isPaid;

    public Payment() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
