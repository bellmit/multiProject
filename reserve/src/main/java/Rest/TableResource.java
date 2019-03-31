package Rest;


import Domain.Table;
import Service.TableService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("table")
public class TableResource {
    @Inject
    private TableService ts;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/new")
    public void addReservation(Table t) {
        ts.addTable(t);
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
    public Response edit(Table table) {
        ts.edit(table);
        return Response.ok().build();
    }

    @DELETE
    @Path("remove/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("uuid") String uuid) {
        Table t = ts.findById(uuid);
        ts.removeTable(t);
        return Response.ok().build();
    }
}
