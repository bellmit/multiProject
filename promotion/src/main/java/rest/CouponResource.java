package rest;

import domain.Coupon;
import rest.auth.JWTHelper;
import rest.auth.Secured;
import service.CouponService;
import service.CouponUsageService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("coupon")
public class CouponResource {

    @Inject
    private CouponService couponService;

    @Inject
    private CouponUsageService couponUsageService;

    @Inject
    private JWTHelper jwtHelper;

    @POST
    @Secured("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/new")
    public Response newCoupon(Coupon coupon) {
        couponService.create(coupon);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{code}")
    @Secured()
    public Response exists(@PathParam("code") String code, @HeaderParam("Authorization") String token) {
        Map<String, String> map = jwtHelper.claimKey(token.substring(7));
        return Response.ok(couponService.findByCode(code, map.get("id"))).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{code}")
    @Secured()
    public Response useCoupon(@PathParam("code") String code, @HeaderParam("Authorization") String token) {
        Map<String, String> map = jwtHelper.claimKey(token.substring(7));
        couponUsageService.useCoupon(code, map.get("id"));
        return Response.ok().build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/edit")
    @Secured("admin")
    public Response edit(Coupon coupon) {
        couponService.edit(coupon);
        return Response.status(Response.Status.OK).entity("Success").build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete")
    @Secured("admin")
    public Response delete(String id) {
        couponService.delete(id);
        return Response.status(Response.Status.OK).entity("Success").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getall")
    @Secured("admin")
    public Response getCouponById() {
        return Response.ok(couponService.getAllCoupons()).build();
    }
}
