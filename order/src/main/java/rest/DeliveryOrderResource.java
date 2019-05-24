package rest;

import domain.DeliveryOrder;
import service.DeliveryOrderService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("deliveryorders")
public class DeliveryOrderResource {
    @Inject
    private DeliveryOrderService as;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/new")
    public DeliveryOrder newDeliveryOrder(DeliveryOrder a) {
        return as.create(a);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public DeliveryOrder getDeliveryOrderById(@PathParam("id") String id) {
        return as.find(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/edit")
    public DeliveryOrder edit(DeliveryOrder a) {
        return as.edit(a);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public void delete(DeliveryOrder a) {
        as.delete(a);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all/{id}")
    public List<DeliveryOrder> getAll(@PathParam("id") String userId){
        return as.getAll(userId);
    }
}
