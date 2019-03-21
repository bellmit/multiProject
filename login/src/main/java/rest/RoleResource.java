package rest;

import domain.Role;
import io.swagger.annotations.Api;
import service.RoleService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Role")
@Path("role")
public class RoleResource {

    @Inject
    RoleService roleService;

    @GET
    @Path("{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("uuid") String uuid) {
        return Response.ok(roleService.find(uuid)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Role role) {
        roleService.create(role);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(Role role) {
        roleService.edit(role);
        return Response.ok().build();
    }

    @DELETE
    @Path("{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("uuid") String uuid) {
        roleService.delete(uuid);
        return Response.ok().build();
    }
}
