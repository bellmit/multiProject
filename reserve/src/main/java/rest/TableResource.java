package rest;


import domain.DiningTable;
import rest.auth.Secured;
import service.TableService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("table")
public class TableResource {
    @Inject
    private TableService ts;

    @POST
    @Path("/new")
    @Secured({"admin", "employee"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addTable(DiningTable t) {
        ts.addTable(t);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response find() {
        return Response.ok(ts.getTablesDTOS()).build();
    }


    @GET
    @Path("get/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("uuid") String uuid) {
        return Response.ok(ts.findById(uuid)).build();
    }

    @PUT
    @Secured({"admin", "employee"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(DiningTable diningTable) {
        ts.edit(diningTable);
        return Response.ok().build();
    }

    @DELETE
    @Path("remove/{uuid}")
    @Secured({"admin", "employee"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("uuid") String uuid) {
        DiningTable t = ts.findById(uuid);
        ts.removeTable(t);
        return Response.noContent().build();
    }

}
