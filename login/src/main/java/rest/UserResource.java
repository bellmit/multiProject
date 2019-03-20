package rest;

import domain.User;
import service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
public class UserResource {

    @Inject
    UserService userService;

    @GET
    @Path("{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("uuid") String uuid) {
        return Response.ok(userService.find(uuid)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(User user) {
        userService.create(user);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(User user) {
        userService.edit(user);
        return Response.ok().build();
    }

    @DELETE
    @Path("{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("uuid") String uuid) {
        userService.delete(uuid);
        return Response.ok().build();
    }

}
