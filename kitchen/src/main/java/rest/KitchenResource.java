package rest;

import messaging.ProducerRabbitMQ;
import service.KitchenService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
public class KitchenResource {
    @Inject
    KitchenService ks;

    @Inject
    ProducerRabbitMQ pr;

    @POST
    @Path("/place")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response placeOrder(){
        return null;
    }

    @GET
    @Path("/health")
    public Response health() {
        return Response.ok().build();
    }

    @GET
    @Path("/mq")
    public Response mq(@QueryParam("msg")String msg) {
        pr.sendMsg(msg, "test");
        return Response.ok().build();
    }
}
