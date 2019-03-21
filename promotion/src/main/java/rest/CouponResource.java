package rest;

import domain.Coupon;
import service.CouponService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("coupon")
public class CouponResource {

    @Inject
    private CouponService cS;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/new")
    public Response newCoupon(Coupon coupon) {
        try {
            cS.create(coupon);
            return Response.status(Response.Status.OK).entity("Success").build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Something went wrong").build();
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getCouponById(@PathParam("id") String id) {
        try {
            Coupon c = cS.find(id);
            return Response.status(Response.Status.OK).entity(new GenericEntity<Coupon>(c) {}).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Something went wrong").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/edit")
    public Response edit(Coupon coupon) {
        try {
            cS.edit(coupon);
            return Response.status(Response.Status.OK).entity("Success").build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Something went wrong").build();
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public Response delete(Coupon coupon) {
        try {
            cS.delete(coupon);
            return Response.status(Response.Status.OK).entity("Success").build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Something went wrong").build();
        }
    }
}
