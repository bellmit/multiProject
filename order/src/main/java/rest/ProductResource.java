package rest;

import domain.Product;
import service.ProductService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("products")
@RequestScoped
public class ProductResource {
    @Inject
    private ProductService as;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/new")
    public Product newProduct(Product a) {
        return as.create(a);
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
    public Product edit(Product a) {
        return as.edit(a);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public void delete(Product a) {
        as.delete(a);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public List<Product> getAll(){
        return as.getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/local")
    public List<Product> getAllLocalProducts(){
        return as.getAllLocalProducts();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deliver")
    public List<Product> getAllDeliveryProducts(){
        return as.getAllDeliveryProducts();
    }
}
