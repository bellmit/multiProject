package rest;

import domain.DeliveryOrder;
import domain.LocalOrder;
import service.LocalOrderService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("localorders")
public class LocalOrderResource {
    @Inject
    private LocalOrderService as;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/new")
    public LocalOrder newLocalOrder(LocalOrder a) {
        return as.create(a);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public LocalOrder getLocalOrderById(@PathParam("id") String id) {
        return as.find(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/edit")
    public LocalOrder edit(LocalOrder a) {
        return as.edit(a);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public void delete(LocalOrder a) {
        as.delete(a);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all/{id}")
    public List<LocalOrder> getAll(@PathParam("id") String userId){
        //todo remove
        System.out.println("banana");
        return as.getAll(userId);
    }
}
