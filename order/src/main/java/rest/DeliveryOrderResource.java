package rest;

import domain.DeliveryOrder;
import service.DeliveryOrderService;
import socket.OrderWebsocket;

import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
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
    @Path("/startdelivery")
    public Response startDelivery(DeliveryOrder d){
        OrderWebsocket orderWebsocket = new OrderWebsocket();
        try {
            return Response.ok(orderWebsocket.updateOrders(d)).build();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
        return Response.ok(false).build();
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
