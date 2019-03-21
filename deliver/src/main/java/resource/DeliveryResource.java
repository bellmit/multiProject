package resource;

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
    @Path("/employee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByEmployee(@PathParam("id") String employeeId){
        try{
            return Response.ok(deliveryService.getByEmployeeId(employeeId)).build();
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
    @Path("/assign")
    @Produces(MediaType.APPLICATION_JSON)
    public Response assignEmployee(@HeaderParam("deliveryId") String deliveryId,
                        @HeaderParam("employeeId") String employeeId){
        try{
            return Response.ok(deliveryService.assignEmployee(deliveryId, employeeId)).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/edit")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editDelivery(@HeaderParam("deliveryId") String deliveryId,
                        @HeaderParam("orderList") List<String> orderList){
        try{
            return Response.ok(deliveryService.editDelivery(deliveryId, orderList)).build();
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
    @Path("/employee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteByEmployee(@PathParam("id") String employeeId){
        try{
            return Response.ok(deliveryService.removeByEmployeeId(employeeId)).build();
        } catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
