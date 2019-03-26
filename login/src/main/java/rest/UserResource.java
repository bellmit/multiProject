package rest;

import domain.User;
import exceptions.UserNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Api("User")
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @GET
    @Path("{uuid}")
    @ApiOperation(value = "Find a user by uuid")
    public Response find(@PathParam("uuid") String uuid) {
        try {
            return Response.ok(userService.find(uuid)).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Create a user")
    public Response add(User user) {
        userService.create(user);
        return Response.created(URI.create(user.getId())).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Edit a user")
    public Response edit(User user) {
        userService.edit(user);
        return Response.ok().build();
    }

    @DELETE
    @Path("{uuid}")
    @ApiOperation(value = "Delete a user by uuid")
    public Response delete(@PathParam("uuid") String uuid) {
        try {
            userService.delete(uuid);
            return Response.ok().build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

}
