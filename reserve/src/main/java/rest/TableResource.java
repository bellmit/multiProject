package rest;


import domain.DinningTable;
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/new")
    public void addTable(DinningTable t) {
        ts.addTable(t);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response find() {
        return Response.ok(ts.getTables()).build();
    }

    @GET
    @Path("get/available")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAvailable() {
        return Response.ok(ts.getAvailableTables()).build();
    }

    @GET
    @Path("get/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("uuid") String uuid) {
        return Response.ok(ts.findById(uuid)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(DinningTable dinningTable) {
        ts.edit(dinningTable);
        return Response.ok().build();
    }

    @DELETE
    @Path("remove/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("uuid") String uuid) {
        DinningTable t = ts.findById(uuid);
        ts.removeTable(t);
        return Response.noContent().build();
    }
}