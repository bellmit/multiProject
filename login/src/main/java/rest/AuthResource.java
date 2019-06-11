package rest;

import domain.User;
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
import java.net.URI;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

@Api("Auth")
@Path("auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    AuthService authService;

    @POST
    @Path("social")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Log a user in")
    public Response socialLogin(JsonObject credential) {
        String token = authService.socialLogin(credential.getString("token"), credential.getString("provider"));
        return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Log a user in")
    public Response login(JsonObject credential) {
        String token = authService.login(credential.getString("email"), credential.getString("password"));
        return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(User user) {
        authService.addUser(user);
        URI email = URI.create(user.getEmail());
        return Response.created(email).build();
    }
}
