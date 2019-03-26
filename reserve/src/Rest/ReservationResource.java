package Rest;

import Domain.Reservation;
import Service.ReservationService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("reservation")
public class ReservationResource {

    @Inject
    private ReservationService rs;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/new")
    public void addReservation(Reservation r) {
        rs.addReservation(r);
    }

    @GET
    @Path("get/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("uuid") UUID uuid) {
        return Response.ok(rs.findById(uuid)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(Reservation reservation) {
        rs.editReservation(reservation);
        return Response.ok().build();
    }

    @DELETE
    @Path("remove/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("uuid") UUID uuid) {
        Reservation r = rs.findById(uuid);
        rs.removeReservation(r);
        return Response.ok().build();
    }

}
