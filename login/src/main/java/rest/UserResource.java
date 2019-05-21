package rest;

import domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("User")
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @GET
    @Path("{uuid}")
    @ApiOperation(value = "Find a user by uuid")
    public Response find(@PathParam("uuid") String uuid) {
        return Response.ok(userService.find(uuid)).build();
    }


    @GET
    @Path("all")
    @ApiOperation(value = "Find all users")
    public Response getAll() {
        return Response.ok(userService.getAll()).build();
    }


    @GET
    @Path("email/{email}")
    @ApiOperation(value = "Find a user by email")
    public Response findByEmail(@PathParam("email") String email) {
        return Response.ok(userService.findByEmail(email)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Edit a user")
    public Response edit(User user) {
        userService.edit(user);
        return Response.ok().build();
    }

    @PUT
    @Path("{uuid}/role/add/{role}")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Edit a user his role")
    public Response editRole(@PathParam("uuid") String uuid, @PathParam("role") String role) {
        userService.assignRole(uuid, role);
        return Response.ok().build();
    }

    @PUT
    @Path("{uuid}/role/remove/{role}")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Edit a user his role")
    public Response removeRole(@PathParam("uuid") String uuid, @PathParam("role") String role) {
        userService.removeRole(uuid, role);
        return Response.ok().build();
    }

    @DELETE
    @Path("{uuid}")
    @ApiOperation(value = "Delete a user by uuid")
    public Response delete(@PathParam("uuid") String uuid) {
        userService.delete(uuid);
        return Response.ok().build();
    }

}
