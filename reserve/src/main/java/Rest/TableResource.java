package Rest;


import Domain.DinningTable;
import Service.TableService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

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
        ArrayList<DinningTable> dinningTables = ts.getTables();
        return Response.ok(dinningTables).build();
    }

    @GET
    @Path("get/available")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAvailable(){
        ArrayList<DinningTable> availableDinningTables = ts.getAvailableTables();
        return Response.ok(availableDinningTables).build();
    }

    @GET
    @Path("get/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("uuid") String uuid) {
        DinningTable foundDinningTable = ts.findById(uuid);
        return Response.ok(foundDinningTable).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(DinningTable dinningTable) {
        DinningTable foundDinningTable = ts.findById(dinningTable.getId());
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
