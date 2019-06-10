package rest;

import io.swagger.annotations.ApiOperation;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("ping")
public class PingResource {

    @Inject
    PingService pingService;

    @GET
    @ApiOperation(value = "Test if the backend is available")
    public Response ping() {
        return Response.ok(pingService.ping()).build();
    }

}
