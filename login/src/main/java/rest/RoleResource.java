package rest;

import domain.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import service.RoleService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Api("Role")
@Path("role")
public class RoleResource {

    @Inject
    RoleService roleService;

    @GET
    @Path("{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Find a role by uuid")
    public Response find(@PathParam("uuid") String uuid) {
        return Response.ok(roleService.find(uuid)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Create a role")
    public Response add(Role role) {
        roleService.create(role);
        return Response.created(URI.create(role.getId())).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Edit a role")
    public Response edit(Role role) {
        roleService.edit(role);
        return Response.ok().build();
    }

    @DELETE
    @Path("{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Delete a role by uuid")
    public Response delete(@PathParam("uuid") String uuid) {
        roleService.delete(uuid);
        return Response.ok().build();
    }
}
