package Rest;


import Domain.TimeSlot;
import Service.TimeSlotService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("timeslot")
public class TimeSlotResource {
    @Inject
    private TimeSlotService tss;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/new")
    public void addTimeSlot(TimeSlot ts) {
        tss.addTimeSlot(ts);
    }

    @GET
    @Path("get/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("uuid") String uuid) {
        return Response.ok(tss.findById(uuid)).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response find() {
        return Response.ok(tss.getTimeSlots()).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(TimeSlot ts) {
        tss.edit(ts);
        return Response.ok().build();
    }

    @DELETE
    @Path("remove/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("uuid") String uuid) {
        TimeSlot ts = tss.findById(uuid);
        tss.removeTimeSlot(ts);
        return Response.noContent().build();
    }
}
