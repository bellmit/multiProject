package Rest;


import Domain.DinningTable;
import Service.TableService;

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
        if(t == null){
            throw new javax.ws.rs.NotFoundException();
        }
        ts.addTable(t);
    }

    @GET
    @Path("get/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("uuid") String uuid) {
        DinningTable foundDinningTable = ts.findById(uuid);
        if(foundDinningTable == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(ts.findById(uuid)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(DinningTable dinningTable) {
        DinningTable foundDinningTable = ts.findById(dinningTable.getId());
        if(foundDinningTable ==null){
            throw new javax.ws.rs.NotFoundException();
        }
        ts.edit(dinningTable);
        return Response.ok().build();
    }

    @DELETE
    @Path("remove/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("uuid") String uuid) {
        DinningTable t = ts.findById(uuid);
        if(t==null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        ts.removeTable(t);
        return Response.noContent().build();
    }
}
