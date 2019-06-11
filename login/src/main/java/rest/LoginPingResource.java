package rest;

import io.swagger.annotations.ApiOperation;
import service.LoginPingService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("test")
public class LoginPingResource {

    @Inject
    LoginPingService pingService;

    @GET
    @ApiOperation(value = "Test the database connection")
    public Response ping() {
        return Response.ok(pingService.ping()).build();
    }

}
