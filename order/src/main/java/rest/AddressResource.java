package rest;

import domain.Address;
import service.AddressService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("address")
public class AddressResource {
    @Inject
    private AddressService as;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/new")
    public void newAddress(Address a) {
        as.create(a);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Address getAddressById(@PathParam("id") String id) {
        return as.find(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/edit")
    public void edit(Address a) {
        as.edit(a);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public void delete(Address a) {
        as.delete(a);
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/all")
//    public List<Address> getAll(){
//        return as.getAll();
//    }
}
