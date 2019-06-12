package resource;

import event.SimulationReceiver;
import domain.Location;
import domain.Route;
import service.interfaces.IDeliveryService;
import util.SimulationHandler;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeoutException;

@Path("deliveries")
public class DeliveryResource {

    private IDeliveryService deliveryService;

    @Inject
    public void setDeliveryService(IDeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            return Response.ok(deliveryService.getAll()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @POST
    @Path("/simulation")
    @Consumes(MediaType.APPLICATION_JSON)
    public void startSimulation(SimulationReceiver simulationReceiver){
        SimulationHandler simulationHandler = new SimulationHandler();
        try {
            simulationHandler.startSimulation(simulationReceiver.getCoords(),simulationReceiver.getOrderId());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/testsim")
    public Response testSimulation() throws IOException, TimeoutException {
        SimulationHandler simulationHandler = new SimulationHandler();
        SimulationReceiver simulationReceiver = new SimulationReceiver("1","(5.482373,51.438115),(5.476627,51.45932)");
        simulationHandler.startSimulation(simulationReceiver.getCoords(),simulationReceiver.getOrderId());
        return Response.ok(true).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSingle(@PathParam("id") String id) {
        try {
            return Response.ok(deliveryService.getById(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/employees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByEmployee(@PathParam("id") String employeeId) {
        try {
            return Response.ok(deliveryService.getByEmployeeId(employeeId)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("{id}/routes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoutes(@PathParam("id") String deliveryId) {
        try {
            return Response.ok(deliveryService.getRoutes(deliveryId)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(@HeaderParam("orderList") Set<String> orderList) {
        try {
            return Response.ok(deliveryService.addDelivery(orderList)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{id}/assign")
    @Produces(MediaType.APPLICATION_JSON)
    public Response assignEmployee(@PathParam("id") String deliveryId,
                                   @HeaderParam("employeeId") String employeeId) {
        try {
            return Response.ok(deliveryService.assignEmployee(deliveryId, employeeId)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{id}/edit")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editDelivery(@PathParam("id") String deliveryId,
                                 @HeaderParam("orderList") Set<String> orderList) {
        try {
            return Response.ok(deliveryService.editDelivery(deliveryId, orderList)).build();
        } catch (Exception e) {
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
                             @HeaderParam("endLocLong") double endLocLong) {
        Route route = new Route(new Location(startLocLat, startLocLong), new Location(endLocLat, endLocLong));
        try {
            return Response.ok(deliveryService.addRoute(deliveryId, route)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSingle(@PathParam("id") String id) {
        try {
            deliveryService.removeById(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/employees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteByEmployee(@PathParam("id") String employeeId) {
        try {
            deliveryService.removeByEmployeeId(employeeId);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
