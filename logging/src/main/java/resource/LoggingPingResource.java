package resource;

import domain.Component;
import io.swagger.annotations.ApiOperation;
import service.LogService;
import service.LoggingPingService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.logging.Level;

@Path("health")
public class LoggingPingResource {

    @Inject
    private LogService logService;

    @Inject
    LoggingPingService pingService;

    @GET
    @ApiOperation(value = "Test the database connection")
    public Response ping() {
        logService.addLog(Component.LOGGING, this.getClass().getName(), "ping resource called", Level.INFO);
        return Response.ok(pingService.ping()).build();
    }

}
