package rest;

import com.stripe.exception.StripeException;
import io.swagger.annotations.ApiOperation;
import service.PaymentService;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("payment")
@Produces(MediaType.APPLICATION_JSON)
public class PaymentResource {

    @Inject
    PaymentService paymentService;


    @POST
    @Path("card")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Create a payment with credit card")
    public Response newCardCharge(JsonObject object)throws StripeException {
        String token = object.getString("token");
        Double amount = Double.parseDouble(object.getString("amount"));
        paymentService.charge(token, amount);
        return Response.ok("success").build();
    }
}
