package resource;

import domain.Location;
import domain.Route;
import service.interfaces.DeliveryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("delivery")
public class DeliveryResource {

    private DeliveryService deliveryService;

    @Inject
    public void setDeliveryService(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSingle(@PathParam("id") String id){
        try{
            return Response.ok(deliveryService.getById(id)).build();
        } catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/employees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByEmployee(@PathParam("id") String employeeId){
        try{
            return Response.ok(deliveryService.getByEmployeeId(employeeId)).build();
        } catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @GET
    @Path("{id}/routes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoutes(@PathParam("id") String deliveryId){
        try{
            return Response.ok(deliveryService.getRoutes(deliveryId)).build();
        } catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(@HeaderParam("orderList") List<String> orderList){
        try{
            return Response.ok(deliveryService.addDelivery(orderList)).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{id}/assign")
    @Produces(MediaType.APPLICATION_JSON)
    public Response assignEmployee(@PathParam("id") String deliveryId,
                                   @HeaderParam("employeeId") String employeeId){
        try{
            return Response.ok(deliveryService.assignEmployee(deliveryId, employeeId)).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{id}/edit")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editDelivery(@PathParam("id") String deliveryId,
                                 @HeaderParam("orderList") List<String> orderList){
        try{
            return Response.ok(deliveryService.editDelivery(deliveryId, orderList)).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{deliveryId}/routes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRoute(@PathParam("deliveryId") String deliveryId,
                             @HeaderParam("startLocLat") double startLocLat,
                             @HeaderParam("startLocLong") double startLocLong,
                             @HeaderParam("endLocLat") double endLocLat,
                             @HeaderParam("endLocLong") double endLocLong){
        Route route = new Route(new Location(startLocLat, startLocLong), new Location(endLocLat, endLocLong));
        try{
            return Response.ok(deliveryService.addRoute(deliveryId, route)).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSingle(@PathParam("id") String id){
        try{
            return Response.ok(deliveryService.removeById(id)).build();
        } catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/employees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteByEmployee(@PathParam("id") String employeeId){
        try{
            return Response.ok(deliveryService.removeByEmployeeId(employeeId)).build();
        } catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
