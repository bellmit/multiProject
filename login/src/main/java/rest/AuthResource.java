package rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import service.AuthService;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

@Api("Auth")
@Path("auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    AuthService authService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Log a user in")
    public Response login(JsonObject credential) {
        String token = authService.login(credential.getString("token"));
        return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();
    }
}
