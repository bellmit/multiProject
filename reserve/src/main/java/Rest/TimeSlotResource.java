package Rest;



import Domain.TimeSlot;
import Service.TimeSlotService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("timeslot")
public class TimeSlotResource {
    @Inject
    private TimeSlotService tss;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/new")
    public void addTimeSlot(TimeSlot ts) {
        if(ts == null){
            throw new javax.ws.rs.NotFoundException();
        }
        tss.addTimeSlot(ts);
    }

    @GET
    @Path("get/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("uuid") String uuid) {
        TimeSlot ts = tss.findById(uuid);
        if (ts == null){
            throw new javax.ws.rs.NotFoundException();
        }
        return Response.ok(tss.findById(uuid)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(TimeSlot ts) {
        if(ts == null){
            throw new javax.ws.rs.NotFoundException();
        }
        tss.edit(ts);
        return Response.ok().build();
    }

    @DELETE
    @Path("remove/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("uuid") String uuid) {
        TimeSlot ts = tss.findById(uuid);
        if(ts == null){
            throw new javax.ws.rs.NotFoundException();
        }
        tss.removeTimeSlot(ts);
        return Response.noContent().build();
    }
}
