package rest;

import domain.OrderStatus;
import service.OrderStatusService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("orderstatus")
public class OrderStatusResource {
    @Inject
    private OrderStatusService as;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/new")
    public void newOrderStatus(OrderStatus a) {
        as.create(a);
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
    public void edit(OrderStatus a) {
        as.edit(a);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public void delete(OrderStatus a) {
        as.delete(a);
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/all")
//    public List<OrderStatus> getAll(){
//        return as.getAll();
//    }
}
