package rest;

import domain.Component;
import io.swagger.annotations.ApiOperation;
import service.LogService;
import service.PingService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.logging.Level;

@Path("ping")
public class PingResource {

    @Inject
    private LogService logService;

    @Inject
    PingService pingService;

    @GET
    @ApiOperation(value = "Test if the backend is available")
    public Response ping() {
        logService.addLog(Component.ORDER, this.getClass().getName(), "ping service called", Level.INFO);
        return Response.ok(pingService.ping()).build();
    }
}
