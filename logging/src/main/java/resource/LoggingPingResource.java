package resource;

import io.swagger.annotations.ApiOperation;
import service.LoggingPingService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("health")
public class LoggingPingResource {

    @Inject
    LoggingPingService pingService;

    @GET
    @ApiOperation(value = "Test the database connection")
    public Response ping() {
        return Response.ok(pingService.ping()).build();
    }

}
