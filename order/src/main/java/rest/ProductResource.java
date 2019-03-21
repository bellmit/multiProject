package rest;

import domain.Product;
import service.ProductService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("product")
public class ProductResource {
    @Inject
    private ProductService as;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/new")
    public void newProduct(Product a) {
        as.create(a);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Product getProductById(@PathParam("id") String id) {
        return as.find(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/edit")
    public void edit(Product a) {
        as.edit(a);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public void delete(Product a) {
        as.delete(a);
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/all")
//    public List<Product> getAll(){
//        return as.getAll();
//    }
}
