package rest;

import domain.Category;
import service.CategoryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("categories")
public class CategoryResource {
    @Inject
    private CategoryService as;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/new")
    public Category newCategory(Category a) {
        return as.create(a);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Category getCategoryById(@PathParam("id") String id) {
        return as.find(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/edit")
    public Category edit(Category a) {
        return as.edit(a);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public void delete(Category a) {
        as.delete(a);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public List<Category> getAll(){
        return as.getAll();
    }
}
