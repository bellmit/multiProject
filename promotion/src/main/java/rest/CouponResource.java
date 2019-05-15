package rest;

import domain.Coupon;
import rest.auth.Secured;
import service.CouponService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("coupon")
public class CouponResource {

    @Inject
    private CouponService cS;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/new")
    public Response newCoupon(Coupon coupon) {
        cS.create(coupon);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getCouponById(@PathParam("id") String id) {
        Coupon c = cS.find(id);
        return Response.status(Response.Status.OK).entity(new GenericEntity<Coupon>(c) {
        }).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/edit")
    public Response edit(Coupon coupon) {
        cS.edit(coupon);
        return Response.status(Response.Status.OK).entity("Success").build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public Response delete(String id) {
        cS.delete(id);
        return Response.status(Response.Status.OK).entity("Success").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getall")
    public Response getCouponById() {
        return Response.ok(cS.getAllCoupons()).build();
    }
}
