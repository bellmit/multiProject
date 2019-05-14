package rest;

import domain.Coupon;
import service.CouponService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("coupon")
public class CouponResource {

    @Inject
    private CouponService cS;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/new")
    public Response newCoupon(Coupon coupon) {
            cS.create(coupon);
            return Response.status(Response.Status.OK).entity("Success").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getCouponById(@PathParam("id") String id) {
            Coupon c = cS.find(id);
            return Response.status(Response.Status.OK).entity(new GenericEntity<Coupon>(c) {}).build();
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
        List<Coupon> coupons = cS.getAllCoupons();
        return Response.status(Response.Status.OK).entity(new GenericEntity<List<Coupon>>(coupons) {}).build();
    }
}
