package rest;

import domain.OrderStatus;
import service.OrderStatusService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("orderstatus")
public class OrderStatusResource {
    @Inject
    private OrderStatusService as;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/new")
    public OrderStatus newOrderStatus(OrderStatus a) {
        return as.create(a);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public OrderStatus getOrderStatusById(@PathParam("id") String id) {
        return as.find(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/edit")
    public OrderStatus edit(OrderStatus a) {
        return as.edit(a);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public void delete(OrderStatus a) {
        as.delete(a);
    }
}
