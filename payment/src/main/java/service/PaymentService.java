package service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class PaymentService {

    @PostConstruct
    private void init() {
        Stripe.apiKey = "sk_test_y5BUAl3mVtRtP4fO7vEpy2TN00BOG1icud";
    }

    public Charge charge(String token, double amount) throws StripeException {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "EUR");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }
}
