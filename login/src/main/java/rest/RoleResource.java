package rest;

import domain.Role;
import exceptions.RoleNotFoundException;
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
@Produces(MediaType.APPLICATION_JSON)
public class RoleResource {

    @Inject
    RoleService roleService;

    @GET
    @Path("{uuid}")
    @ApiOperation(value = "Find a role by uuid")
    public Response find(@PathParam("uuid") String uuid) {
        try {
            return Response.ok(roleService.find(uuid)).build();
        } catch (RoleNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Create a role")
    public Response add(Role role) {
        roleService.create(role);
        return Response.created(URI.create(role.getId())).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Edit a role")
    public Response edit(Role role) {
        roleService.edit(role);
        return Response.ok().build();
    }

    @DELETE
    @Path("{uuid}")
    @ApiOperation(value = "Delete a role by uuid")
    public Response delete(@PathParam("uuid") String uuid) {
        try {
            roleService.delete(uuid);
            return Response.ok().build();
        } catch (RoleNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
