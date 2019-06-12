package rest;

import domain.Reservation;
import service.ReservationService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public Response find(@PathParam("uuid") String uuid) {
        Reservation r = rs.findById(uuid);
        return Response.ok(r).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response find() {
        return Response.ok(rs.getReservations()).build();
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
    public Response delete(@PathParam("uuid") String uuid) {
        Reservation r = rs.findById(uuid);
        rs.removeReservation(r);
        return Response.noContent().build();
    }

}
